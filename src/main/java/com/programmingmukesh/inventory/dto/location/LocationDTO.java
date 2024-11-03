package com.programmingmukesh.inventory.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationDTO {

    private Long id;  // Inherited from BaseEntity

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description should not exceed 100 characters")
    private String description;

    @Size(max = 255, message = "Details should not exceed 255 characters")
    private String details;

    private Long countryId;  // Use only the ID for country reference

    private Long stateId;  // Use only the ID for state reference

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City name should not exceed 50 characters")
    private String city;

    @Size(max = 255, message = "Address should not exceed 255 characters")
    private String address;
}
