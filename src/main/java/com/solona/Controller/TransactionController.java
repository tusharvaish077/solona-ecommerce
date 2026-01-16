package com.solona.Controller;

import com.solona.exception.SellerException;
import com.solona.modal.Seller;
import com.solona.modal.Transaction;
import com.solona.service.SellerService;
import com.solona.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>>getTransactionyBySeller(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Transaction> transactions = transactionService.getTransactionBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(transactions);
    }
}
