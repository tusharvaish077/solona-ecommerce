package com.solona.mapper;

import com.solona.response.ProductResponse;
import com.solona.modal.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setDescription(product.getDescription());

        response.setMrpPrice(product.getMrpPrice());
        response.setSellingPrice(product.getSellingPrice());
        response.setDiscountPercent(product.getDiscountPercent());

        response.setQuantity(product.getQuantity());
        response.setColor(product.getColor());
        response.setImages(product.getImages());

        response.setNumRatings(product.getNumRatings());
        response.setCreatedAt(product.getCreatedAt());
        response.setSizes(product.getSizes());

        // Category
        if (product.getCategory() != null) {

            response.setCategoryId(
                    product.getCategory().getId()
            );

            response.setCategoryName(
                    product.getCategory().getName()
            );
        }

        // Brand
        if (product.getBrand() != null) {

            response.setBrandId(
                    product.getBrand().getId()
            );

            response.setBrandName(
                    product.getBrand().getName()
            );
        }

        // Seller
        if (product.getSeller() != null) {

            response.setSellerId(
                    product.getSeller().getId()
            );
        }

        return response;
    }
}