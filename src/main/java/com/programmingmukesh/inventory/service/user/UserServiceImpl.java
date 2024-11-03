package com.programmingmukesh.inventory.service.user;

import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.User;
import com.programmingmukesh.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            String email = ((User) principal).getUsername();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found in the system"));
        } else {
            throw new ResourceNotFoundException("Authenticated user is not valid");
        }
    }
}
