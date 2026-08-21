package com.solona.service.impl;

import com.solona.response.CategoryResponse;
import com.solona.request.CreateCategoryRequest;
import com.solona.request.UpdateCategoryRequest;
import com.solona.mapper.CategoryMapper;
import com.solona.modal.Category;
import com.solona.repository.CategoryRepository;
import com.solona.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(
            CreateCategoryRequest request) {

        Category parent = getParent(request.getParentId());

        validateParentDepth(parent);

        if (categoryRepository.existsByNameIgnoreCaseAndParentId(
                request.getName(),
                request.getParentId())) {

            throw new RuntimeException(
                    "Category already exists under this parent"
            );
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setImage(request.getImage());
        category.setEnabled(
                request.getEnabled() != null
                        ? request.getEnabled()
                        : true
        );
        category.setDisplayOrder(request.getDisplayOrder());
        category.setParent(parent);

        return categoryMapper.toResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    public CategoryResponse updateCategory(
            Long id,
            UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );

        Category parent = getParent(request.getParentId());

        if (parent != null && isDescendant(parent, category)) {
            throw new RuntimeException(
                    "Category cannot be moved under its own child"
            );
        }
        if (categoryRepository.existsByNameIgnoreCaseAndParentId(
                request.getName(),
                request.getParentId()
        )) {

            Optional<Category> existing =
                    categoryRepository.findByNameIgnoreCaseAndParentId(
                            request.getName(),
                            request.getParentId()
                    );

            if (existing.isPresent()
                    && !existing.get().getId().equals(id)) {

                throw new RuntimeException(
                        "Category already exists under this parent"
                );
            }
        }
        validateParentDepth(parent);

        category.setName(request.getName());
        category.setImage(request.getImage());
        category.setEnabled(request.getEnabled());
        category.setDisplayOrder(request.getDisplayOrder());
        category.setParent(parent);

        return categoryMapper.toResponse(
                categoryRepository.save(category)
        );
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );

        return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> getRootCategories() {

        return categoryRepository
                .findByParentIsNullOrderByDisplayOrderAsc()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> getChildCategories(
            Long parentId) {

        return categoryRepository
                .findByParentIdOrderByDisplayOrderAsc(parentId)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );

        categoryRepository.delete(category);
    }

    @Override
    public void enableCategory(Long id) {

        Category category = getEntity(id);

        category.setEnabled(true);

        categoryRepository.save(category);
    }

    @Override
    public void disableCategory(Long id) {

        Category category = getEntity(id);

        category.setEnabled(false);

        categoryRepository.save(category);
    }

    private Category getEntity(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        )
                );
    }

    private Category getParent(Long parentId) {

        if (parentId == null) {
            return null;
        }

        return categoryRepository.findById(parentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Parent category not found"
                        )
                );
    }

    /*
     * We are keeping the customer UI at 3 levels:
     *
     * Level 0 → Men
     * Level 1 → Topwear
     * Level 2 → T-Shirts
     *
     * Therefore a category cannot have a Level 2 category
     * as its parent.
     */
    private void validateParentDepth(Category parent) {

        if (parent == null) {
            return;
        }

        if (parent.getParent() != null &&
                parent.getParent().getParent() != null) {

            throw new RuntimeException(
                    "Maximum category depth is 3 levels"
            );
        }
    }

    private boolean isDescendant(
            Category possibleParent,
            Category category) {

        Category current = possibleParent;

        while (current != null) {

            if (current.getId().equals(category.getId())) {
                return true;
            }

            current = current.getParent();
        }

        return false;
    }
}
