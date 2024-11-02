package com.programmingmukesh.inventory.service.brand;

import com.programmingmukesh.inventory.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);
    Page<Brand> getAllBrand(Pageable pageable);
    Brand getBrandById(Long id);
    Brand updateBrand(Brand brand, Long id);
    void deleteBrand(Long id);

}
