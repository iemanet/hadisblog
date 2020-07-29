package com.emanet.blog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emanet.blog.model.CommentRmzlhds;
import com.emanet.blog.model.User;

public interface CommentRmzlhdsService {

    CommentRmzlhds save(CommentRmzlhds commentRmzlhds);

    Optional<CommentRmzlhds> findById(Long id);

    void delete(CommentRmzlhds commentRmzlhds);

    Page<CommentRmzlhds> findByUserOrderByCreateDateDesc(User user, int page);

}
