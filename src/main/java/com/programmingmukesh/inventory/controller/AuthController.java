package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.user.UserRequest;
import com.programmingmukesh.inventory.dto.user.UserDto;
import com.programmingmukesh.inventory.mapper.UserMapper;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRequest userRequest){
        User user = authService.createUser(userRequest);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }
}
