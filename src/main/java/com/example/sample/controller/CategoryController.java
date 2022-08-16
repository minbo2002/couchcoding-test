package com.example.sample.controller;

import com.example.sample.controller.dto.CategoryDTO;
import com.example.sample.domain.Category;
import com.example.sample.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Category createCategory(@RequestBody CategoryDTO categoryDTO) {

        return categoryService.create(categoryDTO);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {

        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable, @RequestParam String keyword) {

        return categoryService.getCategories(pageable, keyword);
    }
}
