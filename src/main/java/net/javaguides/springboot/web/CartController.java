package net.javaguides.springboot.web;

import net.javaguides.springboot.global.GlobalData;
import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.OrderRepository;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.ProductService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    private final MongoTemplate mongoTemplate;
    @Autowired
    public CartController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

//    @GetMapping("/addToCart/{id}")
//    public void addToCart(@PathVariable int id){
//        GlobalData.cart.add(productService.getProductById(id));
//    }
    @GetMapping("/addToCart/{id}")
    @ResponseBody
    public void addToCart(@PathVariable String id) {
        System.out.println("Adding product to cart. ID: " + id);
        Product product = productService.getProductById(id);
        product.setQuantity(1);
        GlobalData.cart.add(product);
        System.out.println("Cart contents: " + GlobalData.cart);
    }
//    @GetMapping("/view-cart")
//    public String viewCart(Model model){
//        model.addAttribute("size",GlobalData.cart.size());
//        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//        model.addAttribute("cart",GlobalData.cart);
//        return "cart";
//    }
@GetMapping("/view-cart")
public String viewCart(Model model) {
    Set<Product> uniqueProducts = new HashSet<>(GlobalData.cart);
    model.addAttribute("size", GlobalData.cart.size());
    model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
    model.addAttribute("cart", GlobalData.cart);
    model.addAttribute("uniqueProducts", uniqueProducts);
    return "cart";
}

    @GetMapping("removeItem/{id}")
    public String removeItem(@PathVariable String id) {
        System.out.println("Removing item with ID: " + id);
        // Remove the product from GlobalData.cart based on the product ID
        GlobalData.cart.removeIf(product -> product.getId().equals(new ObjectId(id)));
        System.out.println("Cart contents after removal: " + GlobalData.cart);
        return "redirect:/view-cart";
    }


    @GetMapping("/goToCheckout")
    public String checkout(Model model){
        model.addAttribute("size",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);
        return "checkout";
    }

//    @GetMapping("/completePayment")
//    public String completePayment(Model model) {
//        GlobalData.cart.clear();
//        return "confirm";
//    }
@GetMapping("/completePayment")
public String completePayment(Model model, Authentication authentication) {
    // Get the currently authenticated user
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user = userRepository.findByEmail(userDetails.getUsername());

    List<Order> orderItems = GlobalData.cart.stream()
            .map(product -> {
                Order orderItem = new Order();
                orderItem.set_id(product.getId());
                orderItem.setProductName(product.getProductName());
                orderItem.setUserId(user.getId());
                orderItem.setCategory(product.getCategory());
                orderItem.setPrice(GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
                orderItem.setOrderDate(new Date());
                decreaseProductQuantityInDatabase(product.getId(), 1);
//                order.setTotalAmount(GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

                // Save the order to MongoDB
                orderRepository.save(orderItem);
                return orderItem;
            })
            .collect(Collectors.toList());

//    order.setOrderItems(orderItems);
//    order.setTotalAmount(GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
//
//    // Save the order to MongoDB
//    orderRepository.save(order);

    // Clear the user's cart
    GlobalData.cart.clear();

    return "confirm";
}

//
@PostMapping("/updateQuantity/{id}")
public String updateQuantity(@PathVariable String id, @RequestParam Integer quantity, Model model) {
    // Find the product in the cart
    Product product = GlobalData.cart.stream()
            .filter(p -> p.getId().equals(new ObjectId(id)))
            .findFirst()
            .orElse(null);

    if (product != null) {
        System.out.println(product);
        Integer quantityInDatabase = productRepository.findBy_id(product.getId()).getQuantity();
        System.out.println(quantity + "------" + quantityInDatabase);
        if (quantity <= quantityInDatabase) {
            // Decrease the quantity in the database
            decreaseProductQuantityInDatabase(product.getId(), quantity);

            // Update the quantity in the cart
//            product.setQuantity(quantity);
            GlobalData.cart.add(product);

            // Update total items and total price in the model
            model.addAttribute("size", GlobalData.cart.size());
            model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
            model.addAttribute("cart", GlobalData.cart);
        } else {
            System.out.println("something wrong");
            // Quantity entered by the user is not available in the database
            model.addAttribute("errorMessage", "Desired quantity is not available.");
        }
    }

    return "redirect:/view-cart";
//    return "cart"; // Return the view name directly instead of redirecting to view-cart
}


//    private void decreaseProductQuantityInDatabase(ObjectId productId, int quantity) {
//        // Use the MongoTemplate to update the quantity in the MongoDB database
//        Query query = new Query(Criteria.where("_id").is(productId));
//        Update update = new Update().inc("quantity", -quantity); // Decrease the quantity by the specified amount
//        mongoTemplate.updateFirst(query, update, Product.class);
//    }
private void decreaseProductQuantityInDatabase(ObjectId productId, int quantity) {
    // Use the MongoTemplate to update the quantity in the MongoDB database
    Query query = new Query(Criteria.where("_id").is(productId));
    Update update = new Update().inc("quantity", -quantity); // Decrease the quantity by the specified amount
    mongoTemplate.updateFirst(query, update, Product.class);

    // After updating the quantity, check if it's zero or less
    Product updatedProduct = mongoTemplate.findOne(query, Product.class);
    if (updatedProduct != null && updatedProduct.getQuantity() <= 0) {
        // If quantity is zero or less, set isAvailable to false
        update.set("isAvailable", false);
        mongoTemplate.updateFirst(query, update, Product.class);
    }
//    mongoTemplate.updateFirst(query, update, Product.class);
}



}
