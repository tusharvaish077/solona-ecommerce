package com.solona.service;

import com.solona.exception.ProductException;
import com.solona.modal.Product;
import com.solona.modal.Seller;
import com.solona.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req, Seller seller);
    public void deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product product) throws ProductException;
    public Product findProductById(Long productId) throws ProductException;
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(
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
    List<Product> getProductBySellerId(Long sellerId);
}
