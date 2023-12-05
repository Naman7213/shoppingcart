package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> findOrdersByUserId(ObjectId userId);
    Page<Order> findOrdersByUserId(ObjectId userId, Pageable pageable);
    List<Order> findOrdersByDateRange(Date startDate, Date endDate);
}
