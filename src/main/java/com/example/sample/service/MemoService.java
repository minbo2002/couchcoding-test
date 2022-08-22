package com.example.sample.service;

import com.example.sample.controller.dto.category.CategoryDTO;
import com.example.sample.controller.dto.memo.MemoDTO;
import com.example.sample.controller.dto.memo.MemoFindByIdDto;
import com.example.sample.controller.dto.memo.MemoRequest;
import com.example.sample.domain.Category;
import com.example.sample.domain.Memo;
import com.example.sample.repository.CategoryRepository;
import com.example.sample.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoService {

    private final MemoRepository memoRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public MemoDTO createMemo (MemoDTO memoDTO, Long categoryId) {

        Memo memo = mapToEntity(memoDTO, categoryId);

        log.info("memo : " + memo);

        Memo savedMemo = memoRepository.save(memo);

        return MemoDTO.mapToDto(savedMemo, categoryId);
    }

    @Transactional(readOnly = true)
    public MemoFindByIdDto getMemoById (Long memoId, Long categoryId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));

        return MemoFindByIdDto.builder()
                .categoryDTO(CategoryDTO.mapToDto(category, categoryId))
                .memoDTO(MemoDTO.mapToDto(memo, categoryId))
                .build();
    }

    @Transactional(readOnly = true)
    public Page<Memo> getList(Pageable pageable, String keyword) {

        if (keyword == null) {
            return memoRepository.findAll(pageable);
        } else {
            return memoRepository.findByNameContains(pageable, keyword);
        }
    }

    @Transactional
    public MemoDTO update(Long memoId, Long categoryId, MemoRequest memoRequest) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));

        memo.updateMemo(memoRequest.getName(), memoRequest.getContent());

        return MemoDTO.mapToDto(memo, categoryId);
    }

    @Transactional
    public void delete(Long memoId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));

        memoRepository.delete(memo);
    }

    private Memo mapToEntity(MemoDTO memoDTO, Long categoryId) {

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));

        return Memo.builder()
                .category(findCategory)
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();
    }
}
