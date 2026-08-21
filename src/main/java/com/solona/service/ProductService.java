package com.solona.service;

import com.solona.modal.Product;
import com.solona.response.ProductResponse;
import com.solona.exception.ProductException;
import com.solona.modal.Seller;
import com.solona.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(
            CreateProductRequest req,
            Seller seller
    );

    void deleteProduct(
            Long productId
    ) throws ProductException;

    ProductResponse updateProduct(
            Long productId,
            CreateProductRequest req
    ) throws ProductException;

    ProductResponse findProductById(
            Long productId
    ) throws ProductException;

    List<ProductResponse> searchProducts(
            String query
    );

    Page<ProductResponse> getAllProducts(
            String category,
            String brand,
            String color,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );

    List<ProductResponse> getProductBySellerId(
            Long sellerId
    );
    Product findProductEntityById(Long productId) throws ProductException;
}