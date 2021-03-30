package com.iyzico.challenge.paymentService;

import com.iyzico.challenge.customExceptions.OutOfStockException;
import com.iyzico.challenge.customExceptions.iyzicoPaymentTransactionException;
import com.iyzico.challenge.entity.Payment;
import com.iyzico.challenge.productService.ProductServiceImp;
import com.iyzico.challenge.service.BankPaymentRequest;
import com.iyzico.challenge.service.IyzicoPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

@Transactional
public class PaymentServiceImp implements PaymentService {
    private ProductServiceImp productService ;
    private IyzicoPaymentService iyzicoPaymentService;

    @Transactional
    public HttpStatus buyProducts(Payment payment) {
        payment.setStatus(Payment.Status.InProgress);
        List<Dictionary<Long,Integer>> blockedStocks = new ArrayList<>();
        BigDecimal totalPrice = payment.getPrice();
        for (int index = 0; index<payment.getProducts().size() ; index++){
            Long ProductID = payment.getProducts().get(index).getId();
            Integer ProductQuantity = payment.getProductQuantity().get(index);
             Integer remainingStock = productService.remainingStock(ProductID);
             if(remainingStock < ProductQuantity){
                 throw new OutOfStockException(ProductID);
             }
             else{
                 productService.blockStock(ProductID,ProductQuantity);
                 Dictionary<Long,Integer> blockedInfo = null;
                 blockedInfo.put(ProductID,ProductQuantity);
                 blockedStocks.add(blockedInfo);
             }
            BankPaymentRequest request = new BankPaymentRequest();
             request.setPrice(totalPrice);
        }
        try {
            iyzicoPaymentService.pay(totalPrice);
        }
        catch (Exception ex){
            payment.setStatus(Payment.Status.Error);
            for(Dictionary<Long,Integer> blockedInfo : blockedStocks){
                Long infoKey = blockedInfo.keys().nextElement();
                productService.releaseStock(infoKey,blockedInfo.get(infoKey));
            }
            throw new iyzicoPaymentTransactionException();
        }
        return HttpStatus.OK;
    }
}
