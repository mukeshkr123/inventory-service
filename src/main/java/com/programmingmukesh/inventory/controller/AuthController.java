package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.response.BaseResponse;
import com.programmingmukesh.inventory.dto.user.LoginResponse;
import com.programmingmukesh.inventory.dto.user.LoginUserDTO;
import com.programmingmukesh.inventory.dto.user.UserRequest;
import com.programmingmukesh.inventory.dto.user.UserDTO;
import com.programmingmukesh.inventory.mapper.UserMapper;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.security.JwtUtils;
import com.programmingmukesh.inventory.service.auth.AuthService;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Endpoints for managing user authentication.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserDTO>> register(@Valid @RequestBody UserRequest userRequest) {
        try {
            User user = authService.createUser(userRequest);
            UserDTO userDTO = userMapper.mapToUserDTO(user);
            return ResponseEntity.ok(BaseResponse.success(userDTO));
        } catch (ResourceAlreadyExistsException e) {
            log.warn("Registration failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(BaseResponse.error(e.getMessage()));
        } catch (Exception e) {
            log.error("Error during user registration: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.error("Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            LoginResponse response = new LoginResponse(jwt);
            return ResponseEntity.ok(BaseResponse.success(response));
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponse.error("Invalid email or password"));
        }
    }
}
