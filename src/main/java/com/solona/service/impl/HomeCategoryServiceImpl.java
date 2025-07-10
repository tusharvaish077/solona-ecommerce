package com.solona.service.impl;

import com.solona.modal.HomeCategory;
import com.solona.repository.HomeCategoryRepository;
import com.solona.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {
    public final HomeCategoryRepository homeCategoryRepository;


    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        if(homeCategoryRepository.findAll().isEmpty()){
            return homeCategoryRepository.saveAll(homeCategories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory category, Long id) throws Exception {
        HomeCategory existingCategogy = homeCategoryRepository.findById(id).orElseThrow(()->new Exception("Category not found"));
        if(category.getImage() != null){
            existingCategogy.setImage(category.getImage());
        }
        if(category.getCategoryId() != null){
            existingCategogy.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategogy);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
