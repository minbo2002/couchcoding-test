package com.example.sample.controller.dto;

import com.example.sample.domain.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoRequest {

    private Category category;
    private String name;
    private String content;

    @Builder
    public MemoRequest(Category category, String name, String content) {
        this.category = category;
        this.name = name;
        this.content = content;
    }
}
