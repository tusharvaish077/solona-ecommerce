package com.solona.repository;

import com.solona.modal.Cart;
import com.solona.modal.CartItem;
import com.solona.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
