package com.programmingmukesh.inventory.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String intro;
    private String lastName;
    private String middleName;
    private String mobile;
    private String username;
    private String profile;
    private LocalDateTime lastLogin;
}
