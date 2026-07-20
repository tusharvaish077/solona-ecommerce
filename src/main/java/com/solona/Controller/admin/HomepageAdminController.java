package com.solona.Controller.admin;

import com.solona.dto.CreateHomepageSectionRequest;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.UpdateHomepageSectionRequest;
import com.solona.request.ReorderHomepageSectionsRequest;
import com.solona.service.HomepageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/homepage")
@CrossOrigin
public class HomepageAdminController {

    private final HomepageService homepageService;

    public HomepageAdminController(HomepageService homepageService) {
        this.homepageService = homepageService;
    }

    @GetMapping("/sections")
    public List<HomepageSectionResponse> getAllSections() {
        return homepageService.getAllSections();
    }

    @GetMapping("/sections/{id}")
    public HomepageSectionResponse getSection(@PathVariable Long id) {
        return homepageService.getSectionById(id);
    }

    @PostMapping("/sections")
    public HomepageSectionResponse createSection(
            @RequestBody CreateHomepageSectionRequest request) {

        return homepageService.createSection(request);
    }

    @PutMapping("/sections/{id}")
    public HomepageSectionResponse updateSection(
            @PathVariable Long id,
            @RequestBody UpdateHomepageSectionRequest request) {

        return homepageService.updateSection(id, request);
    }

    @DeleteMapping("/sections/{id}")
    public void deleteSection(@PathVariable Long id) {
        homepageService.deleteSection(id);
    }

    @PatchMapping("/sections/{id}/enable")
    public HomepageSectionResponse enableSection(@PathVariable Long id) {
        return homepageService.enableSection(id);
    }

    @PatchMapping("/sections/{id}/disable")
    public HomepageSectionResponse disableSection(@PathVariable Long id) {
        return homepageService.disableSection(id);
    }

    @PutMapping("/reorder")
    public List<HomepageSectionResponse> reorderSections(
            @RequestBody ReorderHomepageSectionsRequest request) {

        return homepageService.reorderSections(request.getOrderedSectionIds());

    }
}