package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    List<Order> findByUserId(ObjectId userId);
    Page<Order> findByUserId(ObjectId userId, Pageable pageable);
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

}
