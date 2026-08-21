package com.solona.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {

    private Long id;

    private String title;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int discountPercent;

    private int quantity;

    private String color;

    private List<String> images;

    private int numRatings;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    private Long sellerId;

    private LocalDateTime createdAt;

    private String sizes;
}