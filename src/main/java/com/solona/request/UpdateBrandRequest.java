package com.solona.request;

import lombok.Data;

@Data
public class UpdateBrandRequest {

    private String name;

    private String slug;

    private String logo;

    private String banner;

    private String description;

    private String website;

    private Boolean enabled;

    private Integer displayOrder;

}
