package com.programmingmukesh.inventory.dto.subCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryDTO {

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
