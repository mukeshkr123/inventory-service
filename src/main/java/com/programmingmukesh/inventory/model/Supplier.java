package com.programmingmukesh.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Supplier extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(name = "address")
    private String address;

    @Size(max = 100, message = "City cannot exceed 100 characters")
    @Column(name = "city")
    private String city;

    @Size(max = 15, message = "Phone number cannot exceed 15 digits")
    @Column(name = "phone")
    private String phone;

    @Size(max = 15, message = "Mobile number cannot exceed 15 digits")
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 255, message = "Website cannot exceed 255 characters")
    @Column(name = "website")
    private String website;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> suppliedItems;
}
