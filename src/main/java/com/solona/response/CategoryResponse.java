package com.solona.response;

import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

    private String image;

    private Boolean enabled;

    private Integer displayOrder;

    private Long parentId;

    private String parentName;
}