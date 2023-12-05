package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
