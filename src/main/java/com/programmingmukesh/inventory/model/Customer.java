package com.programmingmukesh.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Customer extends BaseEntity {

    @NotBlank(message = "First name must not be empty")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(length = 50)
    private String firstName;

    @Size(max = 50, message = "Middle name must not exceed 50 characters")
    @Column(length = 50)
    private String middleName;

    @NotBlank(message = "Last name must not be empty")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(length = 50)
    private String lastName;

    @Size(max = 15, message = "Mobile number must not exceed 15 characters")
    @Column(length = 15)
    private String mobile;

    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    @Column(length = 50)
    private String email;

    @Size(max = 50, message = "Line 1 must not exceed 50 characters")
    @Column(length = 50)
    private String line1;

    @Size(max = 50, message = "Line 2 must not exceed 50 characters")
    @Column(length = 50)
    private String line2;

    @Size(max = 50, message = "City name must not exceed 50 characters")
    @Column(length = 50)
    private String city;

    @Size(max = 50, message = "Province name must not exceed 50 characters")
    @Column(length = 50)
    private String province;

    @Size(max = 50, message = "Country name must not exceed 50 characters")
    @Column(length = 50)
    private String country;
}
