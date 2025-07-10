package com.solona.repository;

import com.solona.modal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySellerId(Long sellerId);
}
