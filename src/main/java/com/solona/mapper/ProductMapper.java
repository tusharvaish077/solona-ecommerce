package com.solona.mapper;

import com.solona.dto.ProductDto;
import com.solona.modal.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {

        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setMrpPrice(product.getMrpPrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setDiscountPercent(product.getDiscountPercent());
        dto.setQuantity(product.getQuantity());
        dto.setColor(product.getColor());
        dto.setImages(product.getImages());
        dto.setNumRatings(product.getNumRatings());
        dto.setBrandId(
                product.getBrand() != null
                        ? product.getBrand().getId()
                        : null
        );
        dto.setBrandName(
                product.getBrand() != null
                        ? product.getBrand().getName()
                        : null
        );

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getSeller() != null) {
            dto.setSellerId(product.getSeller().getId());
            dto.setSellerName(product.getSeller().getSellerName());
        }

        return dto;
    }
}