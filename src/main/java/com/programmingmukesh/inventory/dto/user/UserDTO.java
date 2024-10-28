package com.programmingmukesh.inventory.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for User information.")
public class UserDTO {

    @Schema(description = "Email of the user.", example = "user@example.com")
    private String email;

    @Schema(description = "First name of the user.", example = "John")
    private String firstName;

    @Schema(description = "Introduction about the user.", example = "Hello, I am John.")
    private String intro;

    @Schema(description = "Last name of the user.", example = "Doe")
    private String lastName;

    @Schema(description = "Middle name of the user.", example = "A.")
    private String middleName;

    @Schema(description = "Mobile number of the user.", example = "+1234567890")
    private String mobile;

    @Schema(description = "Username of the user.", example = "johndoe")
    private String username;

    @Schema(description = "Profile information of the user.", example = "Profile description here.")
    private String profile;

    @Schema(description = "Last login time of the user.")
    private LocalDateTime lastLogin;
}
