package com.programmingmukesh.inventory.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for user registration request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Length(max = 254, message = "Email must not exceed 254 characters")
    @Schema(description = "User's email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Length(max = 50, message = "First name must not exceed 50 characters")
    @Schema(description = "User's first name", example = "John", required = true)
    private String firstName;

    @Length(max = 255, message = "Intro must not exceed 255 characters")
    @Schema(description = "A short introduction or bio of the user", example = "Software Developer at XYZ", required = false)
    private String intro;

    @NotBlank(message = "Last name cannot be blank")
    @Length(max = 50, message = "Last name must not exceed 50 characters")
    @Schema(description = "User's last name", example = "Doe", required = true)
    private String lastName;

    @Length(max = 50, message = "Middle name must not exceed 50 characters")
    @Schema(description = "User's middle name", example = "Michael", required = false)
    private String middleName;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Mobile number should be valid")
    @Length(max = 15, message = "Mobile number must not exceed 15 characters")
    @Schema(description = "User's mobile phone number", example = "+1234567890", required = true)
    private String mobile;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Schema(description = "User's password", example = "Password123", required = true)
    private String password;

    @NotBlank(message = "Username cannot be blank")
    @Length(max = 50, message = "Username must not exceed 50 characters")
    @Schema(description = "User's chosen username", example = "john_doe", required = true)
    private String username;

    @Schema(description = "Profile picture URL", example = "https://example.com/profile.jpg", required = false)
    private String profile;
}
