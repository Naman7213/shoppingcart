package net.javaguides.springboot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
	User findByEmail(String email);
}
