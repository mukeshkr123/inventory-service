package com.programmingmukesh.inventory.dto.item;

import com.programmingmukesh.inventory.model.Brand;
import com.programmingmukesh.inventory.model.Product;
import com.programmingmukesh.inventory.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String description;
    private String sku;
    private Float mrp;
    private Float discount;
    private Float price;
    private Short quantity;
    private Short reorderLevel;
    private Short sold;
    private Short available;
    private Short defective;
    private Product product;
    private Brand brand;
    private Supplier supplier;
}
