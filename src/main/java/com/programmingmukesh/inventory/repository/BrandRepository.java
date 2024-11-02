package com.programmingmukesh.inventory.repository;

import com.programmingmukesh.inventory.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByTitle(String title);
    boolean existsByTitle(String title);
}
