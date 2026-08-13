package com.solona.mapper;


import com.solona.modal.Brand;
import com.solona.request.CreateBrandRequest;
import com.solona.request.UpdateBrandRequest;
import com.solona.response.BrandResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

    public Brand toEntity(CreateBrandRequest request) {

        Brand brand = new Brand();

        brand.setName(request.getName());
        brand.setSlug(request.getSlug());
        brand.setLogo(request.getLogo());
        brand.setBanner(request.getBanner());
        brand.setDescription(request.getDescription());
        brand.setWebsite(request.getWebsite());
        brand.setEnabled(
                request.getEnabled() != null
                        ? request.getEnabled()
                        : true
        );
        brand.setDisplayOrder(
                request.getDisplayOrder() != null
                        ? request.getDisplayOrder()
                        : 0
        );
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());

        return brand;
    }

    public void updateEntity(
            Brand brand,
            UpdateBrandRequest request
    ) {

        brand.setName(request.getName());
        brand.setSlug(request.getSlug());
        brand.setLogo(request.getLogo());
        brand.setBanner(request.getBanner());
        brand.setDescription(request.getDescription());
        brand.setWebsite(request.getWebsite());
        brand.setEnabled(request.getEnabled());
        brand.setDisplayOrder(request.getDisplayOrder());
        brand.setUpdatedAt(LocalDateTime.now());

    }

    public BrandResponse toResponse(Brand brand) {

        BrandResponse response = new BrandResponse();

        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setSlug(brand.getSlug());
        response.setLogo(brand.getLogo());
        response.setBanner(brand.getBanner());
        response.setDescription(brand.getDescription());
        response.setWebsite(brand.getWebsite());
        response.setEnabled(brand.getEnabled());
        response.setDisplayOrder(brand.getDisplayOrder());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());

        return response;

    }

    public List<BrandResponse> toResponseList(
            List<Brand> brands
    ) {

        return brands.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

}