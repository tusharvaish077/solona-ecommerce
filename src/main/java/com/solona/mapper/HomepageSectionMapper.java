package com.solona.mapper;

import com.solona.dto.CreateHomepageSectionRequest;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.UpdateHomepageSectionRequest;
import com.solona.modal.HomepageSection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomepageSectionMapper {

    public HomepageSection toEntity(CreateHomepageSectionRequest request) {

        HomepageSection section = new HomepageSection();

        section.setSectionType(request.getSectionType());
        section.setTitle(request.getTitle());
        section.setDisplayOrder(request.getDisplayOrder());
        section.setEnabled(request.getEnabled());
        section.setConfig(request.getConfig());

        return section;
    }

    public void updateEntity(HomepageSection section,
                             UpdateHomepageSectionRequest request) {

        section.setSectionType(request.getSectionType());
        section.setTitle(request.getTitle());
        section.setDisplayOrder(request.getDisplayOrder());
        section.setEnabled(request.getEnabled());
        section.setConfig(request.getConfig());
    }

    public HomepageSectionResponse toResponse(HomepageSection section) {

        HomepageSectionResponse response = new HomepageSectionResponse();

        response.setId(section.getId());
        response.setSectionType(section.getSectionType());
        response.setTitle(section.getTitle());
        response.setDisplayOrder(section.getDisplayOrder());
        response.setEnabled(section.getEnabled());
        response.setConfig(section.getConfig());

        return response;
    }

    public List<HomepageSectionResponse> toResponseList(List<HomepageSection> sections) {

        return sections.stream()
                .map(this::toResponse)
                .toList();
    }
}