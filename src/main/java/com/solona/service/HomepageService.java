package com.solona.service;

import com.solona.dto.CreateHomepageSectionRequest;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.UpdateHomepageSectionRequest;
import com.solona.modal.HomepageSection;

import java.util.List;

public interface HomepageService {

    // Customer
    List<HomepageSectionResponse> getEnabledSections();

    // Admin
    List<HomepageSectionResponse> getAllSections();

    HomepageSectionResponse getSectionById(Long id);

    HomepageSectionResponse createSection(CreateHomepageSectionRequest request);

    HomepageSectionResponse updateSection(Long id,
                                          UpdateHomepageSectionRequest request);

    void deleteSection(Long id);

    List<HomepageSectionResponse> reorderSections(List<Long> orderedSectionIds);

    HomepageSectionResponse updateDisplayOrder(Long id,
                                               Integer displayOrder);
    HomepageSectionResponse updateStatus(Long id, Boolean enabled);

    HomepageSectionResponse enableSection(Long id);

    HomepageSectionResponse disableSection(Long id);
}