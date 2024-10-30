package com.programmingmukesh.inventory.service.subCategory;

import com.programmingmukesh.inventory.dto.subCategory.SubCategoryDTO;
import com.programmingmukesh.inventory.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory saveSubCategory(SubCategoryDTO subCategoryDTO);

    SubCategory getSubCategoryById(Long id);

    SubCategory updateSubCategory(SubCategoryDTO subCategoryDTO, Long id);

    void deleteSubCategory(Long id);

    List<SubCategory> getAllSubCategories();
}
