package com.solona.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solona.dto.CreateHomepageSectionRequest;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.UpdateHomepageSectionRequest;
import com.solona.modal.HomepageSection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomepageSectionMapper {

    private final ObjectMapper objectMapper;

    public HomepageSectionMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public HomepageSection toEntity(CreateHomepageSectionRequest request) {

        HomepageSection section = new HomepageSection();

        section.setSectionType(request.getSectionType());
        section.setTitle(request.getTitle());
        section.setDisplayOrder(request.getDisplayOrder());
        section.setEnabled(request.getEnabled());

        try {
            section.setConfig(objectMapper.writeValueAsString(request.getConfig()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON Config", e);
        }

        return section;
    }

    public void updateEntity(HomepageSection section,
                             UpdateHomepageSectionRequest request) {

        section.setSectionType(request.getSectionType());
        section.setTitle(request.getTitle());
        section.setDisplayOrder(request.getDisplayOrder());
        section.setEnabled(request.getEnabled());

        try {
            section.setConfig(objectMapper.writeValueAsString(request.getConfig()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON Config", e);
        }
    }

    public HomepageSectionResponse toResponse(HomepageSection section) {

        HomepageSectionResponse response = new HomepageSectionResponse();

        response.setId(section.getId());
        response.setSectionType(section.getSectionType());
        response.setTitle(section.getTitle());
        response.setDisplayOrder(section.getDisplayOrder());
        response.setEnabled(section.getEnabled());

        try {
            JsonNode jsonNode = objectMapper.readTree(section.getConfig());
            response.setConfig(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid JSON Config", e);
        }

        return response;
    }

    public List<HomepageSectionResponse> toResponseList(List<HomepageSection> sections) {

        return sections.stream()
                .map(this::toResponse)
                .toList();
    }
}