package com.solona.repository;

import com.solona.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByCategoryId(String categoryId);


}
