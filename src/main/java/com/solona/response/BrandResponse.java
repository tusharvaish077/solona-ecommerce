package com.solona.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BrandResponse {

    private Long id;

    private String name;

    private String slug;

    private String logo;

    private String banner;

    private String description;

    private String website;

    private Boolean enabled;

    private Integer displayOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}