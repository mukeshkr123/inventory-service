package com.programmingmukesh.inventory.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Length(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Length(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @Length(max = 255, message = "Intro must not exceed 255 characters")
    private String intro;

    @NotBlank(message = "Last name cannot be blank")
    @Length(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Length(max = 50, message = "Middle name must not exceed 50 characters")
    private String middleName;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Mobile number should be valid")
    @Length(max = 15, message = "Mobile number must not exceed 15 characters")
    private String mobile;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @NotBlank(message = "Username cannot be blank")
    @Length(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    private String profile; // Optional: Depending on your application logic
}
