package com.solona.Controller;

import com.solona.response.ProductResponse;
import com.solona.exception.ProductException;
import com.solona.modal.Seller;
import com.solona.request.CreateProductRequest;
import com.solona.service.ProductService;
import com.solona.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {

    private final ProductService productService;

    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProductBySellerId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        Seller seller =
                sellerService.getSellerProfile(jwt);

        List<ProductResponse> products =
                productService.getProductBySellerId(
                        seller.getId()
                );

        return ResponseEntity.ok(products);
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        Seller seller =
                sellerService.getSellerProfile(jwt);

        ProductResponse product =
                productService.createProduct(
                        request,
                        seller
                );

        return new ResponseEntity<>(
                product,
                HttpStatus.CREATED
        );
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId
    ) {

        try {

            productService.deleteProduct(productId);

            return ResponseEntity.ok().build();

        } catch (ProductException e) {

            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody CreateProductRequest request
    ) {

        try {

            ProductResponse updatedProduct =
                    productService.updateProduct(
                            productId,
                            request
                    );

            return ResponseEntity.ok(updatedProduct);

        } catch (ProductException e) {

            return ResponseEntity.notFound().build();
        }
    }
}