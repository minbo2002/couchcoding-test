package com.example.sample.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoDTO {

    private String name;
    private String content;

    @Builder
    public MemoDTO(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
