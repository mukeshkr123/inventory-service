package com.programmingmukesh.inventory.service.category;

import com.programmingmukesh.inventory.dto.category.CategoryDTO;
import com.programmingmukesh.inventory.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(CategoryDTO categoryDTO);
    Category getCategoryById(Long id);
    Category updateCategory(CategoryDTO category, Long id);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
}
