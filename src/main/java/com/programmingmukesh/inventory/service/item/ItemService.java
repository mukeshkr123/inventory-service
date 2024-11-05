package com.programmingmukesh.inventory.service.item;

import com.programmingmukesh.inventory.dto.item.ItemRequestDTO;
import com.programmingmukesh.inventory.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();
    Item createItem(ItemRequestDTO requestDTO);
    Item getItemById(Long id);
    void deleteItem(Long id);
}
