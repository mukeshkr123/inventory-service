package com.programmingmukesh.inventory.dto.item;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemRequestDTO {

    @NotBlank(message = "Description cannot be empty")
    private String description;

    private Long productId;

    private Long brandId;

    private Long supplierId;

    @NotBlank(message = "SKU cannot be empty")
    @Column(length = 100)
    private String sku;

    @PositiveOrZero(message = "MRP must be greater than or equal to zero")
    private Float mrp = 0.0f;

    @PositiveOrZero(message = "Discount must be greater than or equal to zero")
    private Float discount = 0.0f;

    @PositiveOrZero(message = "Price must be greater than or equal to zero")
    private Float price = 0.0f;

    @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
    private Short quantity = 0;

    @Min(value = 0, message = "Reorder level must be greater than or equal to zero")
    private Short reorderLevel;

    @PositiveOrZero(message = "Sold must be greater than or equal to zero")
    private Short sold = 0;

    @PositiveOrZero(message = "Available must be greater than or equal to zero")
    private Short available = 0;

    @PositiveOrZero(message = "Defective must be greater than or equal to zero")
    private Short defective = 0;

}
