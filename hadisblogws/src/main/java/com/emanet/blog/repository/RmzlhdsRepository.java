package com.emanet.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emanet.blog.model.Rmzlhds;

public interface RmzlhdsRepository extends JpaRepository<Rmzlhds, Long> {

    Page<Rmzlhds> findByPageId(Long pageId, Pageable pageable);

    Page<Rmzlhds> findByBodyIgnoreCaseContainingOrSubjectIgnoreCaseContainingOrderByIdAsc(String body, String subject,
        Pageable pageable);

}
