package com.solona.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.solona.domain.SectionType;
import lombok.Data;

import java.util.List;

@Data
public class HomepageSectionResponse {

    private Long id;

    private SectionType sectionType;

    private String title;

    private Integer displayOrder;

    private Boolean enabled;

    private JsonNode config;

    private List<ProductDto> products;
}