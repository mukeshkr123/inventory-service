package com.programmingmukesh.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "states")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class State extends BaseEntity {

    @NotBlank(message = "State name is mandatory")
    @Size(max = 100, message = "State name cannot exceed 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 100, message = "Capital name cannot exceed 100 characters")
    @Column(name = "capital")
    private String capital;

    @NotBlank(message = "State code is mandatory")
    @Size(max = 10, message = "State code cannot exceed 10 characters")
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Size(max = 255, message = "Details cannot exceed 255 characters")
    @Column(name = "details")
    private String details;
}
