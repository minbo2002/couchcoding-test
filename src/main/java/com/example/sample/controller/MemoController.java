package com.example.sample.controller;

import com.example.sample.controller.dto.MemoDTO;
import com.example.sample.controller.dto.MemoRequest;
import com.example.sample.domain.Memo;
import com.example.sample.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/category/{categoryId}/memo")
    public Memo createMemo(@RequestBody MemoDTO memoDTO,
                           @PathVariable Long categoryId) {

        log.info("memoDTO: "+ memoDTO);

        return memoService.create(memoDTO, categoryId);
    }

    // TODO : categoryId pathvariable 기입 -> 비즈니스 로직 정리 필요
    @GetMapping("/category/memo/{memoId}")
    public Memo getMemoById(@PathVariable Long memoId, @PathVariable Long categoryId) {

        return memoService.getMemoById(memoId, categoryId);

    }

    // TODO : 비즈니스 로직 정리 필요
    @GetMapping("/category/{categoryId}/memo")
    public Page<Memo> getMemoList(Pageable pageable, @RequestParam String keyword) {

        return memoService.getList(pageable, keyword);
    }

    // TODO : 비즈니스 로직 정리 필요
    @PatchMapping("/{id}")
    public void updateMemo(@PathVariable Long id, @RequestBody MemoRequest memoRequest) {

        memoService.update(id, memoRequest);
    }

    // TODO : 비즈니스 로직 정리 필요
    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {

        memoService.delete(id);
    }
}
