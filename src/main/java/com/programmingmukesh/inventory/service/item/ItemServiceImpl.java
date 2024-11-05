package com.programmingmukesh.inventory.service.item;

import com.programmingmukesh.inventory.dto.item.ItemRequestDTO;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.Brand;
import com.programmingmukesh.inventory.model.Item;
import com.programmingmukesh.inventory.model.Product;
import com.programmingmukesh.inventory.model.Supplier;
import com.programmingmukesh.inventory.repository.ItemRepository;
import com.programmingmukesh.inventory.service.brand.BrandService;
import com.programmingmukesh.inventory.service.product.ProductService;
import com.programmingmukesh.inventory.service.supplier.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProductService productService;
    private final BrandService brandService;
    private final SupplierService supplierService;

    @Override
    public List<Item> getAllItems() {
        log.info("Fetching all items from the repository");
        List<Item> items = itemRepository.findAll();
        log.info("Retrieved {} items", items.size());
        return items;
    }

    @Override
    public Item createItem(ItemRequestDTO requestDTO) {
        log.info("Creating item with SKU: {}", requestDTO.getSku());

        Product product = validateProduct(requestDTO.getProductId());
        Brand brand = validateBrand(requestDTO.getBrandId());
        Supplier supplier = validateSupplier(requestDTO.getSupplierId());

        Item item = mapToItem(requestDTO, product, brand, supplier);
        Item savedItem = itemRepository.save(item);
        log.info("Item created successfully with ID: {}", savedItem.getId());
        return savedItem;
    }

    @Override
    public Item getItemById(Long id) {
        log.info("Fetching item with ID: {}", id);
        return itemRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Item with ID: {} not found", id);
                    return new ResourceNotFoundException("No such item");
                });
    }

    @Override
    public void deleteItem(Long id) {
        log.info("Attempting to delete item with ID: {}", id);
        Item item = getItemById(id);
        itemRepository.deleteById(id);
        log.info("Item with ID: {} deleted successfully", id);
    }

    private Product validateProduct(Long productId) {
        Product product = productService.getProductById(productId);
        log.debug("Product found: {}", product);
        return product;
    }

    private Brand validateBrand(Long brandId) {
        Brand brand = brandService.getBrandById(brandId);
        log.debug("Brand found: {}", brand);
        return brand;
    }

    private Supplier validateSupplier(Long supplierId) {
        Supplier supplier = supplierService.getSupplierById(supplierId);
        log.debug("Supplier found: {}", supplier);
        return supplier;
    }

    private Item mapToItem(ItemRequestDTO requestDTO, Product product, Brand brand, Supplier supplier) {
        Item item = new Item();
        item.setDescription(requestDTO.getDescription());
        item.setSku(requestDTO.getSku());
        item.setMrp(requestDTO.getMrp());
        item.setDiscount(requestDTO.getDiscount());
        item.setPrice(requestDTO.getPrice());
        item.setQuantity(requestDTO.getQuantity());
        item.setReorderLevel(requestDTO.getReorderLevel());
        item.setSold(requestDTO.getSold());
        item.setAvailable(requestDTO.getAvailable());
        item.setDefective(requestDTO.getDefective());
        item.setProduct(product);
        item.setBrand(brand);
        item.setSupplier(supplier);
        return item;
    }
}
