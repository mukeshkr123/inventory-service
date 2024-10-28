package com.programmingmukesh.inventory.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @Schema(description = "Unique identifier for the product", example = "1")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Schema(description = "Title of the product", example = "Sample Product", required = true)
    private String title;

    @Size(max = 255, message = "Summary must not exceed 255 characters")
    @Schema(description = "Summary of the product", example = "This is a sample product summary")
    private String summary;

    @NotBlank(message = "Content cannot be blank")
    @Schema(description = "Detailed content of the product", required = true)
    private String content;

    @Schema(description = "Subcategory ID of the product", example = "2")
    private Long subcategoryId;

    @NotBlank(message = "Category ID cannot be blank")
    @Schema(description = "Category ID of the product", required = true, example = "3")
    private Long categoryId;
}
