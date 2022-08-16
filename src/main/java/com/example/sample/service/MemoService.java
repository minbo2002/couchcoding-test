package com.example.sample.service;

import com.example.sample.controller.dto.MemoDTO;
import com.example.sample.controller.dto.MemoRequest;
import com.example.sample.domain.Category;
import com.example.sample.domain.Memo;
import com.example.sample.repository.CategoryRepository;
import com.example.sample.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoService {

    private final MemoRepository memoRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Memo create(MemoDTO memoDTO, Long categoryId) {

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));

        log.info("findCategory : " + findCategory);

        Memo memo = Memo.builder()
                .category(findCategory)
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();

        return memoRepository.save(memo);
    }

    @Transactional(readOnly = true)
    public Memo getMemoById(Long memoId, Long categoryId) {

        // TODO 비지니스 로직 정리 필요
        Memo findMemo = memoRepository.findById(memoId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리와 메모가 존재하지 않습니다."));

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));

        return Memo.builder()
                .category(findCategory)
                .name(findMemo.getName())
                .content(findMemo.getContent())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<Memo> getList(Pageable pageable, String keyword) {

        // TODO 비지니스 로직 정리 필요
        if(keyword == null) {
            return memoRepository.findAll(pageable);
        }else {
            return memoRepository.findByNameContains(pageable, keyword);
        }
    }

    @Transactional
    public void update(Long id, MemoRequest memoRequest) {

        // TODO 비지니스 로직 정리 필요
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));

        memo.updateMemo(memoRequest.getCategory(),
                memoRequest.getName(),
                memoRequest.getContent());
    }

    @Transactional
    public void delete(Long id) {

        // TODO 비지니스 로직 정리 필요
        Memo memo = memoRepository.findById(id).orElseThrow(
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

    private MemoDTO mapToDto(Memo memo, Long categoryId) {

        return MemoDTO.builder()
                .name(memo.getName())
                .content(memo.getContent())
                .build();
    }
}
