package com.example.sample.controller.dto.category;

import com.example.sample.domain.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDTO {

    private Long id;
    private String name;

    @Builder
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static public CategoryDTO mapToDto(Category category, Long categoryId) {
        return CategoryDTO.builder()
                .id(categoryId)
                .name(category.getName())
                .build();
    }
}
