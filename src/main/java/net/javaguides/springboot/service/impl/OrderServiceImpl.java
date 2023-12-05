package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.repository.OrderRepository;
import net.javaguides.springboot.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findOrdersByUserId(ObjectId userId) {
        return orderRepository.findByUserId(userId);
    }
    @Override
    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {

        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    @Override
    public Page<Order> findOrdersByUserId(ObjectId userId, Pageable pageable) {
        // Perform the MongoDB query to find orders by user with pagination
        // Use the orderRepository to interact with MongoDB and retrieve the results
        // ...

        return orderRepository.findByUserId(userId, pageable);
    }
}
