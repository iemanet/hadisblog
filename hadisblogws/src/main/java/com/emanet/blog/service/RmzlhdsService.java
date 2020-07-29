package com.emanet.blog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emanet.blog.model.Rmzlhds;

public interface RmzlhdsService {

    Optional<Rmzlhds> findById(Long id);

    Page<Rmzlhds> findByBodyIgnoreCaseContainingOrSubjectIgnoreCaseContainingOrderByIdAsc(String body, String subject,
        int page);

    Page<Rmzlhds> findByPageId(Long pageId, int page);

    Page<Rmzlhds> findAll(int page);

}
