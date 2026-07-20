package com.solona.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.solona.domain.SectionType;
import lombok.Data;

@Data
public class CreateHomepageSectionRequest {

    private SectionType sectionType;

    private String title;

    private Integer displayOrder;

    private Boolean enabled;

    /**
     * JSON configuration for the section.
     * Example:
     * {
     *   "title":"Trending Electronics",
     *   "categoryId":"electronics",
     *   "maxProducts":10
     * }
     */
    private JsonNode config;

}