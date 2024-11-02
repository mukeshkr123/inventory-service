package com.programmingmukesh.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Country extends BaseEntity {

    @NotBlank(message = "Country code must not be empty")
    private String code;

    @NotBlank(message = "Capital must not be empty")
    private String capital;

    private String description;

    @NotBlank(message = "Nationality must not be empty")
    private String nationality;

    private String continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<State> states;

}
