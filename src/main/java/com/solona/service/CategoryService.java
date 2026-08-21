package com.solona.service;

import com.solona.response.CategoryResponse;
import com.solona.request.CreateCategoryRequest;
import com.solona.request.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(
            CreateCategoryRequest request
    );

    CategoryResponse updateCategory(
            Long id,
            UpdateCategoryRequest request
    );

    CategoryResponse getCategoryById(Long id);

    List<CategoryResponse> getAllCategories();

    List<CategoryResponse> getRootCategories();

    List<CategoryResponse> getChildCategories(Long parentId);

    void deleteCategory(Long id);

    void enableCategory(Long id);

    void disableCategory(Long id);
}