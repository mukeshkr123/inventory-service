package com.programmingmukesh.inventory.service.auth;

import com.programmingmukesh.inventory.dto.user.LoginUserDTO;
import com.programmingmukesh.inventory.dto.user.UserRequest;
import com.programmingmukesh.inventory.model.User;

public interface AuthService {
    User createUser(UserRequest user);

    User authenticate(LoginUserDTO loginUserDTO);
}
