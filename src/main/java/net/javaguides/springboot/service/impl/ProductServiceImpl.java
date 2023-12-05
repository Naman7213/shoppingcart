package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Override
//    public Product getProductById(int id){
//        return productRepository.getProductById(id);
//    }

    @Override
    public Product getProductById(String id) {
        System.out.println("Retrieving product by ID: " + id);
        return productRepository.findBy_id(new ObjectId(id));
    }
    @Override
    public List<Product> getFilteredProducts(String category, String minPrice, String maxPrice) {
        Double minPriceValue = parseDouble(minPrice);
        Double maxPriceValue = parseDouble(maxPrice);

        if (category != null && !category.isEmpty()) {
            if (minPriceValue != null && maxPriceValue != null) {
                // Filter by category and price range
                return productRepository.findByCategoryAndPriceBetween(category, minPriceValue, maxPriceValue);
            } else if (minPriceValue != null) {
                // Filter by category and minimum price
                return productRepository.findByCategoryAndPriceGreaterThanEqual(category, minPriceValue);
            } else if (maxPriceValue != null) {
                // Filter by category and maximum price
                return productRepository.findByCategoryAndPriceLessThanEqual(category, maxPriceValue);
            } else {
                // Filter by category only
                return productRepository.findByCategory(category);
            }
        } else {
            if (minPriceValue != null && maxPriceValue != null) {
                // Filter by price range only
                return productRepository.findByPriceBetween(minPriceValue, maxPriceValue);
            } else {
                // No filters, return all products
                return productRepository.findAll();
            }
        }
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    private Double parseDouble(String value) {
        try {
            return value != null && !value.isEmpty() ? Double.parseDouble(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
