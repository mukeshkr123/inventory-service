package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.model.Brand;
import com.programmingmukesh.inventory.service.brand.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Brand Controller", description = "API for managing brands")
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "Create a new brand", description = "Creates a brand with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "409", description = "Brand already exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand createdBrand = brandService.createBrand(brand);
        log.info("Brand created with ID: {}", createdBrand.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @Operation(summary = "Get all brands", description = "Retrieves a list of all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of brands fetched successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class)))
    })
    @GetMapping
    public ResponseEntity<Page<Brand>> getAllBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> brands = brandService.getAllBrand(pageable);
        return ResponseEntity.ok(brands);
    }

    @Operation(summary = "Get brand by ID", description = "Retrieves details of a specific brand by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id);
        log.info("Fetched brand with ID: {}", id);
        return ResponseEntity.ok(brand);
    }

    @Operation(summary = "Update brand", description = "Updates the details of an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Brand with the same title already exists",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@Valid @RequestBody Brand brand, @PathVariable Long id) {
        Brand updatedBrand = brandService.updateBrand(brand, id);
        log.info("Updated brand with ID: {}", id);
        return ResponseEntity.ok(updatedBrand);
    }

    @Operation(summary = "Delete brand", description = "Deletes a specific brand by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        log.info("Deleted brand with ID: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Brand deleted successfully");
    }

}
