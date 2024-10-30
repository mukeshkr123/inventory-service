package com.programmingmukesh.inventory.service.category;

import com.programmingmukesh.inventory.dto.category.CategoryDTO;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.Category;
import com.programmingmukesh.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Saves a new category.
     *
     * @param categoryDTO the data transfer object containing category information
     * @return the saved category
     */
    @Override
    public Category saveCategory(CategoryDTO categoryDTO) {
        log.info("Saving category with title: {}", categoryDTO.getTitle());
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setMetaTitle(categoryDTO.getMetaTitle());
        category.setSlug(categoryDTO.getSlug());
        category.setContent(categoryDTO.getContent());

        Category savedCategory = categoryRepository.save(category);
        log.info("Category '{}' saved successfully", savedCategory.getTitle());
        return savedCategory;
    }

    /**
     * Retrieves a category by ID.
     *
     * @param id the ID of the category
     * @return the found category
     * @throws ResourceNotFoundException if the category is not found
     */
    @Override
    public Category getCategoryById(Long id) {
        log.info("Fetching category with ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Category with ID {} not found", id);
                    return new ResourceNotFoundException("Category not found: " + id);
                });
    }

    /**
     * Updates an existing category by ID.
     *
     * @param categoryDTO the data transfer object containing updated category information
     * @param id          the ID of the category to update
     * @return the updated category
     * @throws ResourceNotFoundException if the category is not found
     */
    @Override
    public Category updateCategory(CategoryDTO categoryDTO, Long id) {
        log.info("Updating category with ID: {}", id);
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Category with ID {} not found for update", id);
                    return new ResourceNotFoundException("Category not found: " + id);
                });

        if (categoryDTO.getTitle() != null) {
            existingCategory.setTitle(categoryDTO.getTitle());
        }
        if (categoryDTO.getMetaTitle() != null) {
            existingCategory.setMetaTitle(categoryDTO.getMetaTitle());
        }
        if (categoryDTO.getSlug() != null) {
            existingCategory.setSlug(categoryDTO.getSlug());
        }
        if (categoryDTO.getContent() != null) {
            existingCategory.setContent(categoryDTO.getContent());
        }

        Category updatedCategory = categoryRepository.save(existingCategory);
        log.info("Category with ID {} updated successfully", id);
        return updatedCategory;
    }

    /**
     * Deletes a category by ID.
     *
     * @param id the ID of the category to delete
     * @throws ResourceNotFoundException if the category is not found
     */
    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);
        if (!categoryRepository.existsById(id)) {
            log.error("Category with ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
        log.info("Category with ID {} deleted successfully", id);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    @Override
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.debug("Fetched {} categories", categories.size());
        return categories;
    }
}
