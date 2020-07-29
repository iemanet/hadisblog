package com.emanet.blog.service.impl;

import com.emanet.blog.model.CommentRmzlhds;
import com.emanet.blog.model.User;
import com.emanet.blog.repository.CommentRmzlhdsRepository;
import com.emanet.blog.service.CommentRmzlhdsService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentRmzlhdsServiceImp implements CommentRmzlhdsService {

    private final int RECORD_PER_PAGE = 10;

    private final CommentRmzlhdsRepository commentRmzlhdsRepository;

    @Autowired
    public CommentRmzlhdsServiceImp(CommentRmzlhdsRepository commentRmzlhdsRepository) {
        this.commentRmzlhdsRepository = commentRmzlhdsRepository;
    }

    @Override
    public CommentRmzlhds save(CommentRmzlhds commentRmzlhds) {
        return commentRmzlhdsRepository.saveAndFlush(commentRmzlhds);
    }

    @Override
    public Optional<CommentRmzlhds> findById(Long id) {
        return commentRmzlhdsRepository.findById(id);
    }

    @Override
    public void delete(CommentRmzlhds commentRmzlhds) {
        commentRmzlhdsRepository.delete(commentRmzlhds);
    }

    @Override
    public Page<CommentRmzlhds> findByUserOrderByCreateDateDesc(User user, int page) {
        return commentRmzlhdsRepository.findByUserOrderByCreateDateDesc(user,
            PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    private int subtractPageByOne(int page) {
        return (page < 1) ? 0 : page - 1;
    }
}
