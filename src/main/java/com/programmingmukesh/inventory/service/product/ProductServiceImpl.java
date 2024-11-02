package com.programmingmukesh.inventory.service.product;

import com.programmingmukesh.inventory.dto.product.ProductRequestDTO;
import com.programmingmukesh.inventory.exceptions.ResourceAlreadyExistsException;
import com.programmingmukesh.inventory.exceptions.ResourceNotFoundException;
import com.programmingmukesh.inventory.model.Category;
import com.programmingmukesh.inventory.model.Product;
import com.programmingmukesh.inventory.model.SubCategory;
import com.programmingmukesh.inventory.repository.CategoryRepository;
import com.programmingmukesh.inventory.repository.ProductRepository;
import com.programmingmukesh.inventory.service.category.CategoryService;
import com.programmingmukesh.inventory.service.subCategory.SubCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    /**
     * Retrieves all products.
 *
     * @return list of products
     */
    @Override
    public List<Product> getProducts() {
        log.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        log.debug("Fetched {} products", products.size());
        return products;
    }

    /**
     * Retrieves a product by ID.
     *
     * @param id product ID
     * @return product with the given ID
     * @throws ResourceNotFoundException if no product is found
     */
    @Override
    public Product getProductById(Long id) {
        log.info("Fetching product with ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with ID {} not found", id);
                    return new ResourceNotFoundException("Product not found");
                });
    }

    /**
     * Saves a new product.
     *
     * @param product product to save
     * @return saved product
     * @throws ResourceAlreadyExistsException if a product with the same title exists
     */
    @Override
    @Transactional
    public Product saveProduct(ProductRequestDTO product) {
        log.info("Saving product with title: {}", product.getTitle());
        if (productRepository.existsByTitle(product.getTitle())) {
            log.error("Product with title '{}' already exists", product.getTitle());
            throw new ResourceAlreadyExistsException("Product already exists");
        }
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setSummary(product.getSummary());
        newProduct.setContent(product.getContent());

        Category category = categoryService.getCategoryById(product.getCategoryId());
        newProduct.setCategory(category);
        SubCategory subCategory = subCategoryService.getSubCategoryById(product.getSubcategoryId());
        newProduct.setSubcategory(subCategory);

        Product savedProduct = productRepository.save(newProduct);
        log.info("Product '{}' saved successfully", savedProduct.getTitle());
        return savedProduct;
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID
     * @throws ResourceNotFoundException if no product is found
     */
    @Override
    @Transactional
    public void removeProduct(Long id) {
        log.info("Deleting product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            log.error("Product with ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
        log.info("Product with ID {} deleted", id);
    }

    /**
     * Updates product details by ID.
     *
     * @param product new product details
     * @param id      ID of product to update
     * @return updated product
     * @throws ResourceNotFoundException if no product is found
     */
    @Override
    @Transactional
    public Product updateProduct(ProductRequestDTO productRequestDTO, Long id) {
        log.info("Updating product with ID: {}", id);
        Product existingProduct = getProductById(id);

        if (productRequestDTO.getTitle() != null) existingProduct.setTitle(productRequestDTO.getTitle());
        if (productRequestDTO.getSummary() != null) existingProduct.setSummary(productRequestDTO.getSummary());
        if (productRequestDTO.getContent() != null) existingProduct.setContent(productRequestDTO.getContent());

        // Update category and subcategory if provided
        if (productRequestDTO.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(productRequestDTO.getCategoryId());
            existingProduct.setCategory(category);
        }

        if (productRequestDTO.getSubcategoryId() != null) {
            SubCategory subCategory = subCategoryService.getSubCategoryById(productRequestDTO.getSubcategoryId());
            existingProduct.setSubcategory(subCategory);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product with ID {} updated successfully", id);
        return updatedProduct;
    }

}
