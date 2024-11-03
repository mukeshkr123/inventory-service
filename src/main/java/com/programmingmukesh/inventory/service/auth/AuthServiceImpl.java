package com.programmingmukesh.inventory.service.auth;

import com.programmingmukesh.inventory.dto.user.UserRequest;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

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
//        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setUsername(userRequest.getUsername());
        return newUser;
    }
}
