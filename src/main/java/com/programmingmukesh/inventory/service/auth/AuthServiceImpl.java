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

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setIntro(user.getIntro());
        newUser.setLastName(user.getLastName());
        newUser.setMiddleName(user.getMiddleName());
        newUser.setMobile(user.getMobile());
        newUser.setPassword(user.getPassword()); // TODO: hash password
        newUser.setUsername(user.getUsername());

        return userRepository.save(newUser);
    }
}
