package com.solona.dto;

import com.solona.modal.Brand;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Integer discountPercent;

    private Integer quantity;

    private String color;

    private List<String> images;

    private String categoryName;

    private Long categoryId;

    private Long sellerId;

    private String sellerName;

    private Integer numRatings;

    private Long brandId;

    private String brandName;

}