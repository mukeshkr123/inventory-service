package com.programmingmukesh.inventory.service.product;

import com.programmingmukesh.inventory.dto.product.ProductRequestDTO;
import com.programmingmukesh.inventory.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product saveProduct(ProductRequestDTO product);
    void removeProduct(Long id);
    Product updateProduct(ProductRequestDTO productRequestDTO, Long id);
    // Todo: product stats
}
