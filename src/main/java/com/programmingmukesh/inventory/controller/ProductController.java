package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.product.ProductRequestDTO;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.Product;
import com.programmingmukesh.inventory.response.BaseResponse;
import com.programmingmukesh.inventory.service.product.ProductService;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product Controller", description = "API for managing products in the inventory")
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return list of all products
     */
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products")
    @GetMapping
    public ResponseEntity<BaseResponse<List<Product>>> getAllProducts() {
        log.info("GET request received for all products");
        List<Product> products = productService.getProducts();
        log.debug("Returning {} products", products.size());
        BaseResponse<List<Product>> response = BaseResponse.success(products);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a product by ID.
     *
     * @param id product ID
     * @return product with the given ID
     */
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product",
                    content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> getProductById(@PathVariable Long id) {
        log.info("GET request received for product with ID: {}", id);
        try {
            Product product = productService.getProductById(id);
            BaseResponse<Product> response = BaseResponse.success(product);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            log.error("Product with ID {} not found", id);
            BaseResponse<Product> response = BaseResponse.error("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Saves a new product.
     *
     * @param product the product to save
     * @return the saved product
     */
    @Operation(summary = "Create a new product", description = "Saves a new product in the inventory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully",
                    content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "409", description = "Product with the same title already exists")
    })
    @PostMapping
    public ResponseEntity<BaseResponse<Product>> createProduct(@Valid @RequestBody ProductRequestDTO productDto) {
        log.info("POST request received to save product: {}", productDto.getTitle());
        try {
            Product savedProduct = productService.saveProduct(productDto);
            BaseResponse<Product> response = BaseResponse.success(savedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceAlreadyExistsException e) {
            log.error("Product with title '{}' already exists", productDto.getTitle());
            BaseResponse<Product> response = BaseResponse.error("Product with the same title already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id      product ID
     * @param product product details to update
     * @return the updated product
     */
    @Operation(summary = "Update a product", description = "Updates an existing product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        log.info("PUT request received to update product with ID: {}", id);
        try {
            Product updatedProduct = productService.updateProduct(productRequestDTO, id);
            BaseResponse<Product> response = BaseResponse.success(updatedProduct);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            log.error("Product with ID {} not found", id);
            BaseResponse<Product> response = BaseResponse.error("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID
     * @return status message
     */
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteProduct(@PathVariable Long id) {
        log.info("DELETE request received for product with ID: {}", id);
        try {
            productService.removeProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            log.error("Product with ID {} not found for deletion", id);
            BaseResponse<Void> response = BaseResponse.error("Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
