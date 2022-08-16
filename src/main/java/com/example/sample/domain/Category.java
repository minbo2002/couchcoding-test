package com.example.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String name;

    @JsonIgnore // 원래는 DTO에서 사용해야한다.
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Memo> memos = new ArrayList<>();

    @Builder
    public Category(String name) {

        this.name = name;
    }
}
