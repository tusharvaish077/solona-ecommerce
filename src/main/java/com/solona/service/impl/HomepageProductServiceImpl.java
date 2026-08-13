package com.solona.service.impl;

import com.solona.dto.ProductSectionConfig;
import com.solona.modal.Product;
import com.solona.repository.ProductRepository;
import com.solona.service.HomepageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomepageProductServiceImpl implements HomepageProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(ProductSectionConfig config) {

        if (config == null || config.getSource() == null) {
            return Collections.emptyList();
        }

        return switch (config.getSource()) {

            case LATEST -> getLatestProducts(config);

            case CATEGORY -> getCategoryProducts(config);

            case SELLER -> getSellerProducts(config);
            case MANUAL -> getManualProducts(config);

            // To be implemented later
            case FEATURED,
                 BEST_SELLING,
                 TRENDING,
                 FLASH_SALE -> Collections.emptyList();

            case BRAND -> getBrandProducts(config);
        };
    }

    private List<Product> getLatestProducts(ProductSectionConfig config) {

        int limit = config.getLimit() != null ? config.getLimit() : 10;

        Pageable pageable = PageRequest.of(
                0,
                limit,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return productRepository.findAll(pageable).getContent();
    }

    private List<Product> getBrandProducts(ProductSectionConfig config) {

        if (config.getBrandId() == null) {
            return Collections.emptyList();
        }

        int limit = config.getLimit() != null
                ? config.getLimit()
                : 10;

        Pageable pageable = PageRequest.of(0, limit);

        return productRepository
                .findByBrandId(config.getBrandId(), pageable)
                .getContent();
    }

    private List<Product> getSellerProducts(ProductSectionConfig config) {

        if (config.getSellerId() == null) {
            return Collections.emptyList();
        }

        int limit = config.getLimit() != null
                ? config.getLimit()
                : 10;

        Pageable pageable = PageRequest.of(0, limit);

        return productRepository
                .findBySellerId(config.getSellerId(), pageable)
                .getContent();
    }
    private List<Product> getCategoryProducts(ProductSectionConfig config) {

        if (config.getCategoryId() == null) {
            return Collections.emptyList();
        }

        int limit = config.getLimit() != null ? config.getLimit() : 10;

        Pageable pageable = PageRequest.of(0, limit);

        return productRepository
                .findByCategoryId(config.getCategoryId(), pageable)
                .getContent();
    }

    private List<Product> getManualProducts(ProductSectionConfig config) {

        if (config.getProductIds() == null ||
                config.getProductIds().isEmpty()) {

            return Collections.emptyList();
        }

        return productRepository.findByIdIn(config.getProductIds());
    }

}