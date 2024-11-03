package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.user.UserDTO;
import com.programmingmukesh.inventory.mapper.UserMapper;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getInfo(){
        User user = userService.getAuthenticatedUser();
        UserDTO response = userMapper.mapToUserDTO(user);
        return ResponseEntity.ok(response);
    }
}
