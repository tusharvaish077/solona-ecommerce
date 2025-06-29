package com.solona.service.impl;

import com.solona.modal.Cart;
import com.solona.modal.CartItem;
import com.solona.modal.User;
import com.solona.repository.CartItemRepository;
import com.solona.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);

        User cartItemuser = item.getCart().getUser();
        if(cartItemuser.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        return null;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItemById(cartItemId);

        User cartItemuser = item.getCart().getUser();
        if(cartItemuser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }
        else throw new Exception("You can't delete this item");
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(()-> new Exception("cart item not found with id "+id));
    }
}
