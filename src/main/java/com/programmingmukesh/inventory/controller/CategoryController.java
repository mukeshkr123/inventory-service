package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.category.CategoryDTO;
import com.programmingmukesh.inventory.response.BaseResponse;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.mapper.CategoryMapper;
import com.programmingmukesh.inventory.model.Category;
import com.programmingmukesh.inventory.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Category Controller", description = "API for managing categories in the inventory")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    /**
     * Retrieves all categories.
     *
     * @return list of all categories
     */
    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of categories")
    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryDTO>>> getAllCategories() {
        log.info("GET request received for all categories");
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(categoryMapper::mapToCategoryDTO)  // Assuming you have a mapper to convert Category to CategoryDTO
                .collect(Collectors.toList());
        log.debug("Returning {} categories", categoryDTOS.size());

        BaseResponse<List<CategoryDTO>> response = new BaseResponse<>(true, null, categoryDTOS);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a category by ID.
     *
     * @param id category ID
     * @return category with the given ID
     */
    @Operation(summary = "Get category by ID", description = "Retrieves a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the category",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        log.info("GET request received for category with ID: {}", id);
        try {
            Category category = categoryService.getCategoryById(id);
            CategoryDTO categoryDTO = categoryMapper.mapToCategoryDTO(category);
            BaseResponse<CategoryDTO> response = new BaseResponse<>(true, null, categoryDTO);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            log.error("Category with ID {} not found", id);
            BaseResponse<CategoryDTO> response = new BaseResponse<>(false, "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Saves a new category.
     *
     * @param categoryDTO the category data to save
     * @return the saved category
     */
    @Operation(summary = "Create a new category", description = "Saves a new category in the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "409", description = "Category already exists")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<CategoryDTO>> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.info("POST request received to save category: {}", categoryDTO.getTitle());
        try {
            Category savedCategory = categoryService.saveCategory(categoryDTO);
            CategoryDTO savedCategoryDTO = categoryMapper.mapToCategoryDTO(savedCategory);
            BaseResponse<CategoryDTO> response = new BaseResponse<>(true, null, savedCategoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceAlreadyExistsException e) {
            log.error("Category with title '{}' already exists", categoryDTO.getTitle());
            BaseResponse<CategoryDTO> response = new BaseResponse<>(false, "Category already exists", null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    /**
     * Updates an existing category by ID.
     *
     * @param id          category ID
     * @param categoryDTO updated category details
     * @return the updated category
     */
    @Operation(summary = "Update a category", description = "Updates an existing category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryDTO>> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        log.info("PUT request received to update category with ID: {}", id);
        try {
            Category updatedCategory = categoryService.updateCategory(categoryDTO, id);
            CategoryDTO updatedCategoryDTO = categoryMapper.mapToCategoryDTO(updatedCategory);
            BaseResponse<CategoryDTO> response = new BaseResponse<>(true, null, updatedCategoryDTO);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            log.error("Category with ID {} not found", id);
            BaseResponse<CategoryDTO> response = new BaseResponse<>(false, "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Deletes a category by ID.
     *
     * @param id category ID
     * @return status message
     */
    @Operation(summary = "Delete a category", description = "Deletes a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteCategory(@PathVariable Long id) {
        log.info("DELETE request received for category with ID: {}", id);
        try {
            categoryService.deleteCategory(id);
            BaseResponse<Void> response = new BaseResponse<>(true, null, null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (ResourceNotFoundException e) {
            log.error("Category with ID {} not found for deletion", id);
            BaseResponse<Void> response = new BaseResponse<>(false, "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
