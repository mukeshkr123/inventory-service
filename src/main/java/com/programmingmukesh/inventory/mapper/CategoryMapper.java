package com.programmingmukesh.inventory.mapper;

import com.programmingmukesh.inventory.dto.category.CategoryDTO;
import com.programmingmukesh.inventory.model.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    public Category mapToCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO mapToCategoryDTO(Category category){
        return  modelMapper.map(category, CategoryDTO.class);
    }
}
