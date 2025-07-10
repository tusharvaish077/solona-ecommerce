package com.solona.service;

import com.solona.modal.Order;
import com.solona.modal.Seller;
import com.solona.modal.Transaction;
import java.util.*;

public interface TransactionService {
    Transaction  createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransaction();
}
