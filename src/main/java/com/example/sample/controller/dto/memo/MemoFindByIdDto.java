package com.example.sample.controller.dto.memo;

import com.example.sample.controller.dto.category.CategoryDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemoFindByIdDto {

    private CategoryDTO categoryDTO;
    private MemoDTO memoDTO;

    @Builder
    public MemoFindByIdDto(CategoryDTO categoryDTO, MemoDTO memoDTO) {
        this.categoryDTO = categoryDTO;
        this.memoDTO = memoDTO;
    }
}
