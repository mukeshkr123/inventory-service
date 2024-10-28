package com.programmingmukesh.inventory.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LonginRequestDto {
    private String username;
    private String password;
}
