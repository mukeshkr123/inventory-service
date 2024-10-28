package com.programmingmukesh.inventory.service.product;

import com.programmingmukesh.inventory.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product removeProduct(Long id);
    Product updateProduct(Product product, Long id);
}
