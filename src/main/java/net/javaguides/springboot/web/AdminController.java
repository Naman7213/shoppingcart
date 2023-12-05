package net.javaguides.springboot.web;

import net.javaguides.springboot.model.Admin;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.AdminRepository;
import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.service.AdminService;
import net.javaguides.springboot.service.CategoryService;
import net.javaguides.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin")
    public String adminPage() {
        return "admindashboard";
    }

    @GetMapping("/login-admin-page")
    public String adminLoginPage(){
        return "adminloginpage";
    }

    @PostMapping("/loginAsAdmin")
    public String loginAsAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
        Admin admin = adminRepository.findByEmail(email);
        System.out.println(admin);

        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println(admin.getPassword()+"-------"+password);
            return "admindashboard"; // Redirect to admin dashboard
        } else {
            System.out.println("else");
            model.addAttribute("error", true);
            return "adminloginpage";
        }
    }

    @GetMapping("/addCategoryForm")
    public String showAddCategoryPage() {
        return "addcategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName, Model model) {

        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
        model.addAttribute("successMessage", "Category added successfully!");

        return "admindashboard";
    }

    @GetMapping("/addProductForm")
    public String showAddProductPage(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("imgUrl") String imgUrl,
            @RequestParam("category") String category,
            @RequestParam("price") Double price,
            @RequestParam("description") String description,
            @RequestParam("isAvailable") boolean isAvailable,
            @RequestParam("quantity") Integer quantity,
            Model model
    ) {
        // Create a new Product instance
        Product product = new Product();
        product.setProductName(productName);
        product.setImgUrl(imgUrl);
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setAvailable(isAvailable);
        product.setQuantity(quantity);

        // Save the product to the database
        productService.saveProduct(product);
        System.out.println(product);

        // Add a success message to the model
        model.addAttribute("successMessageProduct", "Product added successfully!");

        // Redirect back to the admin dashboard
        return "admindashboard";
    }


    @GetMapping("/logout")
    public String logout(){
        return "adminloginpage";
    }
}
