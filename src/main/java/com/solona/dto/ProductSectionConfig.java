package com.solona.dto;

import com.solona.enums.ProductSourceType;
import lombok.Data;

import java.util.List;

@Data
public class ProductSectionConfig {

    private ProductSourceType source;

    private Integer limit;

    private Long categoryId;

    private Long brandId;

    private List<Long> productIds;

    private Long sellerId;
    // getters/setters
}