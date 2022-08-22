package com.example.sample.controller.dto.memo;

import com.example.sample.domain.Memo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoDTO {

    private Long categoryId;
    private Long id;
    private String name;
    private String content;

    @Builder
    public MemoDTO(Long categoryId, Long id, String name, String content) {
        this.categoryId = categoryId;
        this.id = id;
        this.name = name;
        this.content = content;
    }

    static public MemoDTO mapToDto(Memo memo, Long categoryId) {

        return MemoDTO.builder()
                .categoryId(categoryId)
                .id(memo.getId())
                .name(memo.getName())
                .content(memo.getContent())
                .build();
    }

}
