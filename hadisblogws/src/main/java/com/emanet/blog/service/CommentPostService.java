package com.emanet.blog.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emanet.blog.model.CommentPost;
import com.emanet.blog.model.User;

public interface CommentPostService {

    CommentPost save(CommentPost commentPost);

    Optional<CommentPost> findById(Long id);

    void delete(CommentPost commentPost);

    Page<CommentPost> findByUserOrderByCreateDateDesc(User user, int page);
}
