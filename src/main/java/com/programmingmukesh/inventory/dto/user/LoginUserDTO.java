package com.programmingmukesh.inventory.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginUserDTO {
    private String email;
    private String password;
}
