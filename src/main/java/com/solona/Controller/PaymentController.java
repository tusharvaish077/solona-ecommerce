package com.solona.Controller;

import com.solona.modal.*;
import com.solona.response.ApiResponse;
import com.solona.response.PaymentLinkResponse;
import com.solona.service.PaymentService;
import com.solona.service.SellerReportService;
import com.solona.service.SellerService;
import com.solona.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        PaymentLinkResponse paymentResponse;

        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess = paymentService.ProcessedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId);
        if(paymentSuccess){
            for(Order order:paymentOrder.getOrders()){
//                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }

        }
        ApiResponse res = new ApiResponse();
        res.setMessage("Payment Successful");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
