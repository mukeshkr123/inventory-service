package com.programmingmukesh.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Item extends BaseEntity {

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private Supplier supplier;

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
    private Short reorder_level;

    @PositiveOrZero(message = "Sold must be greater than or equal to zero")
    private Short sold = 0;

    @PositiveOrZero(message = "Available must be greater than or equal to zero")
    private Short available = 0;

    @PositiveOrZero(message = "Defective must be greater than or equal to zero")
    private Short defective = 0;

    public void setQuantity(Short quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
