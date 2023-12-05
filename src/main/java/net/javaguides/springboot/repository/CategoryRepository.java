package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

}
