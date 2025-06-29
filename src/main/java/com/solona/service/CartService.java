package com.solona.service;

import com.solona.modal.Cart;
import com.solona.modal.CartItem;
import com.solona.modal.Product;
import com.solona.modal.User;

public interface CartService {

    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
}
