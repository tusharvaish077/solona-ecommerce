package com.solona.service.impl;

import com.solona.exception.ResourceNotFoundException;
import com.solona.modal.Brand;
import com.solona.repository.BrandRepository;
import com.solona.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {

        return brandRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Brand not found with id : " + id
                        ));
    }

    @Override
    public Brand createBrand(Brand brand) {

        brand.setId(null);
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());

        if (brand.getEnabled() == null) {
            brand.setEnabled(true);
        }

        if (brand.getDisplayOrder() == null) {
            brand.setDisplayOrder(0);
        }

        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand updatedBrand) {

        Brand brand = getBrandById(id);

        brand.setName(updatedBrand.getName());
        brand.setSlug(updatedBrand.getSlug());
        brand.setLogo(updatedBrand.getLogo());
        brand.setBanner(updatedBrand.getBanner());
        brand.setDescription(updatedBrand.getDescription());
        brand.setWebsite(updatedBrand.getWebsite());
        brand.setEnabled(updatedBrand.getEnabled());
        brand.setDisplayOrder(updatedBrand.getDisplayOrder());
        brand.setUpdatedAt(LocalDateTime.now());

        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {

        Brand brand = getBrandById(id);

        brandRepository.delete(brand);
    }

}