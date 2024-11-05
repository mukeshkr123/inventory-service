package com.programmingmukesh.inventory.service.brand;

import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.Brand;
import com.programmingmukesh.inventory.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brandReq) {
        brandRepository.findByTitle(brandReq.getTitle()).ifPresent(brand -> {
            throw new ResourceAlreadyExistsException("Brand with title '" + brandReq.getTitle() + "' already exists");
        });

        Brand brand = mapToEntity(brandReq);
        Brand savedBrand = brandRepository.save(brand);
        log.info("Created new brand with ID: {}", savedBrand.getId());
        return savedBrand;
    }

    @Override
    public Page<Brand> getAllBrand(Pageable pageable) {
        Page<Brand> brands = brandRepository.findAll(pageable);
        log.info("Fetched brands from database");
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand with ID " + id + " not found"));
    }

    @Override
    public Brand updateBrand(Brand brandReq, Long id) {
        Brand existingBrand = getBrandById(id);

        if (!existingBrand.getTitle().equals(brandReq.getTitle()) && brandRepository.existsByTitle(brandReq.getTitle())) {
            throw new ResourceAlreadyExistsException("Brand with title '" + brandReq.getTitle() + "' already exists");
        }

        updateBrandEntity(existingBrand, brandReq);
        Brand updatedBrand = brandRepository.save(existingBrand);
        log.info("Updated brand with ID: {}", id);
        return updatedBrand;
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = getBrandById(id);
        brandRepository.delete(brand);
        log.info("Deleted brand with ID: {}", id);
    }

    private Brand mapToEntity(Brand brandReq) {
        Brand brand = new Brand();
        brand.setTitle(brandReq.getTitle());
        brand.setContent(brandReq.getContent());
        brand.setSummary(brandReq.getSummary());
        return brand;
    }

    private void updateBrandEntity(Brand brand, Brand brandReq) {
        brand.setTitle(brandReq.getTitle());
        brand.setContent(brandReq.getContent());
        brand.setSummary(brandReq.getSummary());
    }
}
