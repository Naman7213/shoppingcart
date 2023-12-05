//package net.javaguides.springboot.web;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class ProductController {
//}
package net.javaguides.springboot.web;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


//    @GetMapping("/filter")
//    public String getFilteredProducts(@RequestParam(name = "category", required = false) String category, Model model) {
//        List<Product> filteredProducts;
//
//        if (category != null && !category.isEmpty()) {
//            filteredProducts = productRepository.findByCategory(category);
//        } else {
//            // If no category is provided, return all products
//            filteredProducts = productRepository.findAll();
//        }
//
//        model.addAttribute("products", filteredProducts);
//        return "index";
//    }
@GetMapping("/filter")
public String getFilteredProducts(@RequestParam(required = false) String category,
                                  @RequestParam(required = false) String minPrice,
                                  @RequestParam(required = false) String maxPrice,
                                  Model model) {
    List<Product> filteredProducts = productService.getFilteredProducts(category, minPrice, maxPrice);
    System.out.println("Filtered Products: " + filteredProducts);
    model.addAttribute("products", filteredProducts);
    return "index";
}
}
