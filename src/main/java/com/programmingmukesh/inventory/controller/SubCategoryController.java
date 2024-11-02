package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.subCategory.SubCategoryDTO;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.SubCategory;
import com.programmingmukesh.inventory.service.subCategory.SubCategoryService;
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

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "SubCategory Controller", description = "API for managing subcategories in the inventory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    /**
     * Retrieves all subcategories.
     *
     * @return list of all subcategories
     */
    @Operation(summary = "Get all subcategories", description = "Retrieves a list of all subcategories")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of subcategories")
    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        log.info("GET request received for all subcategories");
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        log.debug("Returning {} subcategories", subCategories.size());
        return ResponseEntity.ok(subCategories);
    }

    /**
     * Retrieves a subcategory by ID.
     *
     * @param id subcategory ID
     * @return subcategory with the given ID
     */
    @Operation(summary = "Get subcategory by ID", description = "Retrieves a subcategory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the subcategory",
                    content = @Content(schema = @Schema(implementation = SubCategory.class))),
            @ApiResponse(responseCode = "404", description = "Subcategory not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        log.info("GET request received for subcategory with ID: {}", id);
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            return ResponseEntity.ok(subCategory);
        } catch (ResourceNotFoundException e) {
            log.error("Subcategory with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Saves a new subcategory.
     *
     * @param subCategoryDTO the subcategory to save
     * @return the saved subcategory
     */
    @Operation(summary = "Create a new subcategory", description = "Saves a new subcategory in the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subcategory created successfully",
                    content = @Content(schema = @Schema(implementation = SubCategory.class))),
            @ApiResponse(responseCode = "409", description = "Subcategory already exists")
    })
    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) {
        log.info("POST request received to save subcategory: {}", subCategoryDTO);
        try {
            SubCategory savedSubCategory = subCategoryService.saveSubCategory(subCategoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSubCategory);
        } catch (ResourceAlreadyExistsException e) {
            log.error("Subcategory with details '{}' already exists", subCategoryDTO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    /**
     * Updates an existing subcategory by ID.
     *
     * @param id               subcategory ID
     * @param subCategoryDTO   subcategory details to update
     * @return the updated subcategory
     */
    @Operation(summary = "Update a subcategory", description = "Updates an existing subcategory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory updated successfully",
                    content = @Content(schema = @Schema(implementation = SubCategory.class))),
            @ApiResponse(responseCode = "404", description = "Subcategory not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @Valid @RequestBody SubCategoryDTO subCategoryDTO) {
        log.info("PUT request received to update subcategory with ID: {}", id);
        try {
            SubCategory updatedSubCategory = subCategoryService.updateSubCategory(subCategoryDTO, id);
            return ResponseEntity.ok(updatedSubCategory);
        } catch (ResourceNotFoundException e) {
            log.error("Subcategory with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Deletes a subcategory by ID.
     *
     * @param id subcategory ID
     * @return status message
     */
    @Operation(summary = "Delete a subcategory", description = "Deletes a subcategory by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subcategory deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subcategory not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        log.info("DELETE request received for subcategory with ID: {}", id);
        try {
            subCategoryService.deleteSubCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            log.error("Subcategory with ID {} not found for deletion", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
