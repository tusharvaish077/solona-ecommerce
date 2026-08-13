package com.solona.Controller;

import com.solona.modal.Brand;
import com.solona.request.CreateBrandRequest;
import com.solona.request.UpdateBrandRequest;
import com.solona.mapper.BrandMapper;
import com.solona.response.BrandResponse;
import com.solona.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    private final BrandMapper brandMapper;

    @GetMapping("/api/brands")
    public List<BrandResponse> getAllBrands() {

        return brandMapper.toResponseList(
                brandService.getAllBrands()
        );

    }

    @GetMapping("/api/brands/{id}")
    public BrandResponse getBrandById(
            @PathVariable Long id
    ) {

        return brandMapper.toResponse(
                brandService.getBrandById(id)
        );

    }

    @PostMapping("/admin/brands")
    public BrandResponse createBrand(
            @RequestBody CreateBrandRequest request
    ) {

        Brand brand = brandMapper.toEntity(request);

        return brandMapper.toResponse(
                brandService.createBrand(brand)
        );

    }

    @PutMapping("/admin/brands/{id}")
    public BrandResponse updateBrand(
            @PathVariable Long id,
            @RequestBody UpdateBrandRequest request
    ) {

        Brand brand = brandService.getBrandById(id);

        brandMapper.updateEntity(brand, request);

        return brandMapper.toResponse(
                brandService.updateBrand(id, brand)
        );

    }

    @DeleteMapping("/admin/brands/{id}")
    public void deleteBrand(
            @PathVariable Long id
    ) {

        brandService.deleteBrand(id);

    }

}