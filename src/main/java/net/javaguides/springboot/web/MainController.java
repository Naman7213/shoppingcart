package net.javaguides.springboot.web;

import net.javaguides.springboot.global.GlobalData;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;
	@GetMapping("/")
	public String home(Model model) {
		List<Product> allProducts = productRepository.findAll();
		model.addAttribute("products", allProducts);
		model.addAttribute("size", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
