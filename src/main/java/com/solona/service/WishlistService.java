package com.solona.service;

import com.solona.modal.Product;
import com.solona.modal.User;
import com.solona.modal.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
