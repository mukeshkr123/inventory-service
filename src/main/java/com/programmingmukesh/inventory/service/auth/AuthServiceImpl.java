package com.programmingmukesh.inventory.service.auth;

import com.programmingmukesh.inventory.dto.user.LoginUserDTO;
import com.programmingmukesh.inventory.dto.user.UserRequest;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRequest user) {
        log.info("Attempting to create user with email: {}", user.getEmail());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists.");
        }
        if (userRepository.existsByMobile(user.getMobile())) {
            throw new ResourceAlreadyExistsException("Mobile number already exists.");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists.");
        }

        User newUser = buildNewUser(user);
        return userRepository.save(newUser);
    }

    @Override
    public User authenticate(LoginUserDTO loginUserDTO) {
        log.info("Authenticating user with email: {}", loginUserDTO.getEmail());

        User user = userRepository.findByEmail(loginUserDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        log.info("User found: {}", user);

        // Attempt authentication
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getEmail(),
                        loginUserDTO.getPassword()
                )
        );

        return user;
    }

    /**
     * Helper method to build a new User entity from UserRequest DTO.
     */
    private User buildNewUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setIntro(userRequest.getIntro());
        newUser.setLastName(userRequest.getLastName());
        newUser.setMiddleName(userRequest.getMiddleName());
        newUser.setMobile(userRequest.getMobile());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setUsername(userRequest.getUsername());
        return newUser;
    }
}
