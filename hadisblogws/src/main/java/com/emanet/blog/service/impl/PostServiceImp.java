package com.emanet.blog.service.impl;

import com.emanet.blog.model.Post;
import com.emanet.blog.model.User;
import com.emanet.blog.repository.PostRepository;
import com.emanet.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImp implements PostService {
    
    private final int RECORD_PER_PAGE = 10;

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public Page<Post> findByUserOrderByCreateDateDesc(User user, int page) {
        return postRepository.findByUserOrderByCreateDateDesc(user, PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    @Override
    public Page<Post> findAllByOrderByCreateDateDesc(int page) {
        return postRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page) {
        return (page < 1) ? 0 : page - 1;
    }
}
