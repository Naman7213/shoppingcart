package net.javaguides.springboot.web;

import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.OrderService;
import net.javaguides.springboot.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private final OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-history")
    public String getOrderHistory(Model model,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Use the username to find the user (assuming email is the username)
        User user = userRepository.findByEmail(username);

        // Fetch the ObjectId of the logged-in user
        ObjectId userId = user.getId();

        // Use the userId to find the orders
//        model.addAttribute("orders", orderService.findOrdersByUserId(userId));
        Page<Order> orderPage = orderService.findOrdersByUserId(userId, PageRequest.of(page, size));
        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        return "orderhistory";
    }

    @GetMapping("/search-orders")
    public String searchOrders(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                               Model model) {
        // Perform the search based on startDate and endDate
        List<Order> orders = orderService.findOrdersByDateRange(startDate, endDate);

        // Add the search results to the model
        model.addAttribute("orders", orders);

        // Add the start and end dates for display in the result page
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "orderhistory";
    }
}
