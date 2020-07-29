package com.emanet.blog.service.impl;

import com.emanet.blog.model.CommentPost;
import com.emanet.blog.model.User;
import com.emanet.blog.repository.CommentPostRepository;
import com.emanet.blog.service.CommentPostService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentPostServiceImp implements CommentPostService {

    private final int RECORD_PER_PAGE = 10;

    private final CommentPostRepository commentPostRepository;

    @Autowired
    public CommentPostServiceImp(CommentPostRepository commentPostRepository) {
        this.commentPostRepository = commentPostRepository;
    }

    @Override
    public CommentPost save(CommentPost commentPost) {
        return commentPostRepository.saveAndFlush(commentPost);
    }

    @Override
    public Optional<CommentPost> findById(Long id) {
        return commentPostRepository.findById(id);
    }

    @Override
    public void delete(CommentPost commentPost) {
        commentPostRepository.delete(commentPost);
    }

    @Override
    public Page<CommentPost> findByUserOrderByCreateDateDesc(User user, int page) {
        return commentPostRepository.findByUserOrderByCreateDateDesc(user,
            PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    private int subtractPageByOne(int page) {
        return (page < 1) ? 0 : page - 1;
    }
}
