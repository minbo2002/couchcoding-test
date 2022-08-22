package com.example.sample.service;

import com.example.sample.controller.dto.category.CategoryDTO;
import com.example.sample.domain.Category;
import com.example.sample.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(CategoryDTO categoryDTO) {

        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());

        if(findOne.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이름입니다.");
        }

        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();

        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable, String keyword) {

        if(keyword == null) {
            return categoryRepository.findAll(pageable);
        }else {
            return categoryRepository.findByNameContains(pageable, keyword);
        }
    }
}
