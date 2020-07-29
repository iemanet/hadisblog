package com.emanet.blog.service;

import com.emanet.blog.model.Post;
import com.emanet.blog.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    Optional<Post> findById(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderByCreateDateDesc(User user, int page);

    Page<Post> findAllByOrderByCreateDateDesc(int page);

    void delete(Post post);
}
