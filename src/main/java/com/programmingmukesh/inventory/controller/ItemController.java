package com.programmingmukesh.inventory.controller;

import com.programmingmukesh.inventory.dto.item.ItemDTO;
import com.programmingmukesh.inventory.dto.item.ItemRequestDTO;
import com.programmingmukesh.inventory.mapper.ItemMapper;
import com.programmingmukesh.inventory.model.Item;
import com.programmingmukesh.inventory.response.BaseResponse;
import com.programmingmukesh.inventory.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public ResponseEntity<BaseResponse<List<ItemDTO>>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        List<ItemDTO> itemDTOs = items.stream()
                .map(itemMapper::itemToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.success(itemDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ItemDTO>> getItemById(@PathVariable Long id) {
        log.info("Received request to fetch item with ID: {}", id);
        Item item = itemService.getItemById(id);

        // Convert Item entity to ItemDTO
        ItemDTO itemDTO = itemMapper.itemToDTO(item);

        return ResponseEntity.ok(BaseResponse.success(itemDTO));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ItemDTO>> createItem(@Valid @RequestBody ItemRequestDTO requestDTO) {
        Item createdItem = itemService.createItem(requestDTO);

        // Convert Item entity to ItemDTO
        ItemDTO itemDTO = itemMapper.itemToDTO(createdItem);

        return ResponseEntity.ok(BaseResponse.success(itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }
}
