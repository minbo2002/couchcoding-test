package com.example.sample.repository;

import com.example.sample.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    Page<Memo> findByNameContains(Pageable pageable, String name);

}
