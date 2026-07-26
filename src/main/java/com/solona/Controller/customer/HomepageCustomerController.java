package com.solona.Controller.customer;

import com.solona.dto.HomepageSectionResponse;
import com.solona.modal.HomepageSection;
import com.solona.service.HomepageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/homepage")
@RequiredArgsConstructor
public class HomepageCustomerController {

    private final HomepageService homepageService;

    @GetMapping
    public ResponseEntity<List<HomepageSectionResponse>> getHomepage() {

        return ResponseEntity.ok(
                homepageService.getEnabledSections()
        );

    }

}