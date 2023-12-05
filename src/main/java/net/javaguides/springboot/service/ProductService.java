package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getFilteredProducts(String category, String minPrice, String maxPrice);

//    Product getProductById(int id);
    Product getProductById(String id);
    void saveProduct(Product product);
}
