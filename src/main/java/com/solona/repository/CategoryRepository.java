package com.solona.repository;

import com.solona.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNullOrderByDisplayOrderAsc();

    List<Category> findByParentIdOrderByDisplayOrderAsc(Long parentId);

    List<Category> findByEnabledTrueOrderByDisplayOrderAsc();

    boolean existsByNameIgnoreCaseAndParentId(
            String name,
            Long parentId
    );

    Optional<Category> findByNameIgnoreCaseAndParentId(
            String name,
            Long parentId
    );
    List<Category> findByParentIsNullAndEnabledTrueOrderByDisplayOrderAsc();
    List<Category> findByParentIdAndEnabledTrueOrderByDisplayOrderAsc(
            Long parentId
    );
}