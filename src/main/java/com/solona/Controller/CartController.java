package com.solona.Controller;

import com.solona.modal.Cart;
import com.solona.modal.CartItem;
import com.solona.modal.Product;
import com.solona.modal.User;
import com.solona.request.AddItemRequest;
import com.solona.response.ApiResponse;
import com.solona.service.CartItemService;
import com.solona.service.CartService;
import com.solona.service.ProductService;
import com.solona.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        System.out.println("cart - "+cart.getUser().getEmail());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(
                user,
                product,
                req.getSize(),
                req.getQuantity()
                );
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added To Cart Successfully ");
        return new ResponseEntity<>(item , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item Removed from Cart Successfully");
        return new ResponseEntity<ApiResponse>( res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String jwt


    )throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        CartItem updatedCartItem = null;
        if(cartItem.getQuantity()>0){
            updatedCartItem = cartItemService.updateCartItem(user.getId(),cartItemId, cartItem);
        }
        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
    }

}
