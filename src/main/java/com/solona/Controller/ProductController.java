package com.solona.Controller;

import com.solona.response.ProductResponse;
import com.solona.exception.ProductException;
import com.solona.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long productId
    ) throws ProductException {

        ProductResponse product =
                productService.findProductById(productId);

        return new ResponseEntity<>(
                product,
                HttpStatus.OK
        );
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(
            @RequestParam(required = false) String query
    ) {

        List<ProductResponse> products =
                productService.searchProducts(query);

        return new ResponseEntity<>(
                products,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(

            @RequestParam(required = false)
            String category,

            @RequestParam(required = false)
            String brand,

            @RequestParam(required = false)
            String color,

            @RequestParam(required = false)
            String size,

            @RequestParam(required = false)
            Integer minPrice,

            @RequestParam(required = false)
            Integer maxPrice,

            @RequestParam(required = false)
            Integer minDiscount,

            @RequestParam(required = false)
            String sort,

            @RequestParam(required = false)
            String stock,

            @RequestParam(defaultValue = "0")
            Integer pageNumber

    ) {

        Page<ProductResponse> products =
                productService.getAllProducts(
                        category,
                        brand,
                        color,
                        size,
                        minPrice,
                        maxPrice,
                        minDiscount,
                        sort,
                        stock,
                        pageNumber
                );

        return new ResponseEntity<>(
                products,
                HttpStatus.OK
        );
    }
}