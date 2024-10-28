package com.programmingmukesh.inventory.repository;

import com.programmingmukesh.inventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);
}
