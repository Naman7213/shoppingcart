//package net.javaguides.springboot.repository;
//
//import net.javaguides.springboot.model.Product;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//public interface ProductRepository extends MongoRepository<Product, ObjectId> {
//}

package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByCategoryAndPriceGreaterThanEqual(String category, Double minPrice);

    List<Product> findByCategoryAndPriceLessThanEqual(String category, Double maxPrice);

    List<Product> findByCategoryAndPriceBetween(String category, Double minPrice, Double maxPrice);

//    Product getProductById(int id);
    Product findBy_id(ObjectId _id);
}
