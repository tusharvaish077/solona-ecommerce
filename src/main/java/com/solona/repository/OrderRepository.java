package com.solona.repository;

import com.solona.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long sellerId);
}
