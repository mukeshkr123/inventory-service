package com.programmingmukesh.inventory.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating a new product.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Size(max = 255, message = "Summary must not exceed 255 characters")
    private String summary;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

    @NotNull(message = "Subcategory ID cannot be null")
    private Long subcategoryId;
}
