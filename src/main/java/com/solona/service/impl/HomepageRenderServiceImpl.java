package com.solona.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solona.dto.HomepageSectionResponse;
import com.solona.dto.ProductDto;

import com.solona.dto.ProductSectionConfig;
import com.solona.mapper.ProductMapper;
import com.solona.modal.Product;
import com.solona.response.ProductResponse;
import com.solona.service.HomepageProductService;
import com.solona.service.HomepageRenderService;
import com.solona.service.HomepageService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomepageRenderServiceImpl
        implements HomepageRenderService {

    private final HomepageService homepageService;

    private final HomepageProductService homepageProductService;

    private final ObjectMapper objectMapper;

    private final ProductMapper productMapper;


    @Override
    public List<HomepageSectionResponse> buildHomepage() {

        List<HomepageSectionResponse> sections =
                homepageService.getEnabledSections();

        if (sections == null || sections.isEmpty()) {
            return Collections.emptyList();
        }

        sections.forEach(this::enrichSection);

        return sections;
    }


    private void enrichSection(
            HomepageSectionResponse section
    ) {

        if (section == null ||
                section.getSectionType() == null) {

            return;
        }

        switch (section.getSectionType()) {

            case PRODUCT_CAROUSEL:
            case PRODUCT_GRID:
            case FLASH_SALE:

                attachProducts(section);

                break;

            default:
                break;
        }
    }


    private void attachProducts(HomepageSectionResponse section) {

        try {

            if (section.getConfig() == null) {
                section.setProducts(List.of());
                return;
            }

            ProductSectionConfig config =
                    objectMapper.treeToValue(
                            section.getConfig(),
                            ProductSectionConfig.class
                    );

            if (config == null || config.getSource() == null) {
                section.setProducts(List.of());
                return;
            }

            List<Product> products =
                    homepageProductService.getProducts(config);

            if (products == null || products.isEmpty()) {
                section.setProducts(List.of());
                return;
            }

            List<ProductResponse> productResponses =
                    products.stream()
                            .map(productMapper::toResponse)
                            .toList();

            section.setProducts(productResponses);

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Failed to build homepage section: "
                            + section.getId(),
                    ex
            );
        }
    }
}