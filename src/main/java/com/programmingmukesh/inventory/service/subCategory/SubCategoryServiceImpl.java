package com.programmingmukesh.inventory.service.subCategory;

import com.programmingmukesh.inventory.dto.subCategory.SubCategoryDTO;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.SubCategory;
import com.programmingmukesh.inventory.repository.SubCategoryRepository;
import com.programmingmukesh.inventory.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    /**
     * Saves a new subcategory.
     *
     * @param subCategoryDTO the data transfer object containing subcategory information
     * @return the saved subcategory
     */
    @Override
    public SubCategory saveSubCategory(SubCategoryDTO subCategoryDTO) {
        log.info("Saving subcategory with description: {}", subCategoryDTO.getDescription());
        SubCategory subCategory = new SubCategory();
        subCategory.setDescription(subCategoryDTO.getDescription());

        // Set the associated category
        subCategory.setCategory(categoryService.getCategoryById(subCategoryDTO.getCategoryId()));

        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
        log.info("Subcategory '{}' saved successfully", savedSubCategory.getDescription());
        return savedSubCategory;
    }

    /**
     * Retrieves a subcategory by ID.
     *
     * @param id the ID of the subcategory
     * @return the found subcategory
     * @throws ResourceNotFoundException if the subcategory is not found
     */
    @Override
    public SubCategory getSubCategoryById(Long id) {
        log.info("Fetching subcategory with ID: {}", id);
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Subcategory with ID {} not found", id);
                    return new ResourceNotFoundException("Subcategory not found: " + id);
                });
    }

    /**
     * Updates an existing subcategory by ID.
     *
     * @param subCategoryDTO the data transfer object containing updated subcategory information
     * @param id             the ID of the subcategory to update
     * @return the updated subcategory
     * @throws ResourceNotFoundException if the subcategory is not found
     */
    @Override
    public SubCategory updateSubCategory(SubCategoryDTO subCategoryDTO, Long id) {
        log.info("Updating subcategory with ID: {}", id);
        SubCategory existingSubCategory = getSubCategoryById(id);

        if (subCategoryDTO.getDescription() != null) {
            existingSubCategory.setDescription(subCategoryDTO.getDescription());
        }

        // Update the category only if the category ID is provided
        if (subCategoryDTO.getCategoryId() != null) {
            existingSubCategory.setCategory(categoryService.getCategoryById(subCategoryDTO.getCategoryId()));
        }

        SubCategory updatedSubCategory = subCategoryRepository.save(existingSubCategory);
        log.info("Subcategory with ID {} updated successfully", id);
        return updatedSubCategory;
    }

    /**
     * Deletes a subcategory by ID.
     *
     * @param id the ID of the subcategory to delete
     * @throws ResourceNotFoundException if the subcategory is not found
     */
    @Override
    public void deleteSubCategory(Long id) {
        log.info("Deleting subcategory with ID: {}", id);
        if (!subCategoryRepository.existsById(id)) {
            log.error("Subcategory with ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Subcategory not found: " + id);
        }
        subCategoryRepository.deleteById(id);
        log.info("Subcategory with ID {} deleted successfully", id);
    }

    /**
     * Retrieves all subcategories.
     *
     * @return a list of all subcategories
     */
    @Override
    public List<SubCategory> getAllSubCategories() {
        log.info("Fetching all subcategories");
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        log.debug("Fetched {} subcategories", subCategories.size());
        return subCategories;
    }
}
