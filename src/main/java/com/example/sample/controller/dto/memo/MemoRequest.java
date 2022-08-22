package com.example.sample.controller.dto.memo;

import com.example.sample.domain.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoRequest {

    private String name;
    private String content;

    @Builder
    public MemoRequest(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
