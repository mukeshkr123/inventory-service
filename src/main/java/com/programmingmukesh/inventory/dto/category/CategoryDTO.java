package com.programmingmukesh.inventory.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Category entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Size(max = 100, message = "Meta title must not exceed 100 characters")
    private String metaTitle;

    @NotBlank(message = "Slug cannot be blank")
    @Size(max = 50, message = "Slug must not exceed 50 characters")
    private String slug;

    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content;
}
