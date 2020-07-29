package com.emanet.blog.repository;

import com.emanet.blog.model.CommentPost;
import com.emanet.blog.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostRepository extends JpaRepository<CommentPost, Long> {

    Page<CommentPost> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

}
