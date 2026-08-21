package com.solona.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {

    private String title;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int quantity;

    private String color;

    private List<String> images;

    private Long categoryId;

    private String sizes;

    private Long brandId;
}