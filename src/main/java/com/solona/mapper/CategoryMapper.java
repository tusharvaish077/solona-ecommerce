package com.solona.mapper;

import com.solona.response.CategoryResponse;
import com.solona.modal.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {

        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setName(category.getName());
        response.setImage(category.getImage());
        response.setEnabled(category.getEnabled());
        response.setDisplayOrder(category.getDisplayOrder());

        if (category.getParent() != null) {
            response.setParentId(category.getParent().getId());
            response.setParentName(category.getParent().getName());
        }

        return response;
    }
}