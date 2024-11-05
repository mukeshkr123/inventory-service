package com.programmingmukesh.inventory.mapper;

import com.programmingmukesh.inventory.dto.item.ItemDTO;
import com.programmingmukesh.inventory.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final ModelMapper modelMapper;

    public ItemDTO itemToDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }
}
