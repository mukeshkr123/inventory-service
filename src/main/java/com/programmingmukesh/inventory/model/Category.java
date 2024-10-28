package com.programmingmukesh.inventory.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Category extends BaseEntity {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @ToString.Include
    private String title;

    @Size(max = 100, message = "Meta title must not exceed 100 characters")
    private String metaTitle;

    @NotBlank(message = "Slug cannot be blank")
    @Size(max = 50, message = "Slug must not exceed 50 characters")
    @ToString.Include
    private String slug;

    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<SubCategory> subCategories;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> products;

}
