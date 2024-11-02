package com.programmingmukesh.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "common_objects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonObject extends BaseEntity {

    @NotBlank(message = "Object type must not be empty")
    private String objectType;

    private String description;

    private String details;
}
