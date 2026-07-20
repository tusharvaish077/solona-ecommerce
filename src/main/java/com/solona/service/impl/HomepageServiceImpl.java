package com.solona.service.impl;

import com.solona.dto.CreateHomepageSectionRequest;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.UpdateHomepageSectionRequest;
import com.solona.exception.ResourceNotFoundException;
import com.solona.mapper.HomepageSectionMapper;
import com.solona.modal.HomepageSection;
import com.solona.repository.HomepageSectionRepository;
import com.solona.service.HomepageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageServiceImpl implements HomepageService {

    private final HomepageSectionRepository repository;
    private final HomepageSectionMapper mapper;

    public HomepageServiceImpl(HomepageSectionRepository repository,
                               HomepageSectionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Private helper method.
     * Used internally whenever we need the Entity.
     */
    private HomepageSection getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Homepage Section not found with id : " + id));
    }

    @Override
    public List<HomepageSectionResponse> getEnabledSections() {

        List<HomepageSection> sections =
                repository.findByEnabledTrueOrderByDisplayOrderAsc();

        return mapper.toResponseList(sections);
    }

    @Override
    public List<HomepageSectionResponse> getAllSections() {

        List<HomepageSection> sections =
                repository.findAllByOrderByDisplayOrderAsc();

        return mapper.toResponseList(sections);
    }

    @Override
    public HomepageSectionResponse getSectionById(Long id) {

        HomepageSection section = getEntityById(id);

        return mapper.toResponse(section);
    }

    @Override
    public HomepageSectionResponse createSection(CreateHomepageSectionRequest request) {

        HomepageSection section = mapper.toEntity(request);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

    @Override
    public HomepageSectionResponse updateSection(Long id,
                                                 UpdateHomepageSectionRequest request) {

        HomepageSection section = getEntityById(id);

        mapper.updateEntity(section, request);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

    @Override
    public void deleteSection(Long id) {

        HomepageSection section = getEntityById(id);

        repository.delete(section);
    }



    @Override
    public List<HomepageSectionResponse> reorderSections(List<Long> orderedSectionIds) {

        for (int i = 0; i < orderedSectionIds.size(); i++) {

            HomepageSection section = getEntityById(orderedSectionIds.get(i));

            section.setDisplayOrder(i + 1);

            repository.save(section);
        }

        return mapper.toResponseList(
                repository.findAllByOrderByDisplayOrderAsc()
        );
    }

    @Override
    public HomepageSectionResponse updateDisplayOrder(Long id,
                                                      Integer displayOrder) {

        HomepageSection section = getEntityById(id);

        section.setDisplayOrder(displayOrder);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

    @Override
    public HomepageSectionResponse updateStatus(Long id, Boolean enabled) {
        HomepageSection section = getEntityById(id);

        section.setEnabled(enabled);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

    @Override
    public HomepageSectionResponse enableSection(Long id) {
        HomepageSection section = getEntityById(id);

        section.setEnabled(Boolean.TRUE);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

    @Override
    public HomepageSectionResponse disableSection(Long id) {
        HomepageSection section = getEntityById(id);

        section.setEnabled(Boolean.FALSE);

        section = repository.save(section);

        return mapper.toResponse(section);
    }

}