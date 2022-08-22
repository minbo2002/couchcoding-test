package com.example.sample.controller;

import com.example.sample.controller.dto.memo.MemoDTO;
import com.example.sample.controller.dto.memo.MemoFindByIdDto;
import com.example.sample.controller.dto.memo.MemoRequest;
import com.example.sample.domain.Memo;
import com.example.sample.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/category/{categoryId}/memo")
    public ResponseEntity<MemoDTO> createMemo(@RequestBody MemoDTO memoDTO,
                           @PathVariable Long categoryId) {

        log.info("memoDTO: "+ memoDTO);

        MemoDTO createMemo = memoService.createMemo(memoDTO, categoryId);

        return new ResponseEntity<>(createMemo, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/memo/{memoId}")
    public ResponseEntity<MemoFindByIdDto> getMemoById(@PathVariable Long memoId,
                                                       @PathVariable Long categoryId) {

        return ResponseEntity.ok(memoService.getMemoById(memoId, categoryId));

    }

    @GetMapping("/category/memo")
    public Page<Memo> getMemoList(Pageable pageable,
                                  @RequestParam String keyword) {

        return memoService.getList(pageable, keyword);
    }

    @PatchMapping("/category/{categoryId}/memo/{memoId}")
    public ResponseEntity<?> updateMemo(@PathVariable Long memoId,
                           @PathVariable Long categoryId,
                           @RequestBody MemoRequest memoRequest) {

        return ResponseEntity.ok(memoService.update(memoId, categoryId, memoRequest));
    }

    @DeleteMapping("/memo/{memoId}")
    public ResponseEntity<?> deleteMemo(@PathVariable Long memoId) {

        memoService.delete(memoId);

        return ResponseEntity.ok("메모 삭제가 완료되었습니다.");
    }
}
