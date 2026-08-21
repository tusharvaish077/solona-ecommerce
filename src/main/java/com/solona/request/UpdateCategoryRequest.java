package com.solona.request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {

    private String name;

    private Long parentId;

    private String image;

    private Boolean enabled;

    private Integer displayOrder;
}