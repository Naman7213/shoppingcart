package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Admin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, ObjectId> {
    Admin findByEmail(String username);
}
