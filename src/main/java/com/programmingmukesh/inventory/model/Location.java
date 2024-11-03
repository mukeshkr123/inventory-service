package com.programmingmukesh.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Location extends BaseEntity {

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description should not exceed 100 characters")
    private String description;

    @Size(max = 255, message = "Details should not exceed 255 characters")
    private String details;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City name should not exceed 50 characters")
    private String city;

    @Size(max = 255, message = "Address should not exceed 255 characters")
    private String address;
}
