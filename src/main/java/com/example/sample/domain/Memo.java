package com.example.sample.domain;

import com.example.sample.controller.dto.memo.MemoDTO;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Memo(Category category, String name, String content) {
        this.category = category;
        this.name = name;
        this.content = content;
    }

    public void updateMemo(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
