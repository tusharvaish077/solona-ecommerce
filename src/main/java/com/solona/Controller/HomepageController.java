package com.solona.Controller;

import com.solona.dto.HomepageSectionResponse;
import com.solona.service.HomepageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homepage")
@CrossOrigin
public class HomepageController {

    private final HomepageService homepageService;

    public HomepageController(HomepageService homepageService) {
        this.homepageService = homepageService;
    }

    @GetMapping
    public List<HomepageSectionResponse> getHomepage() {
        return homepageService.getEnabledSections();
    }
}