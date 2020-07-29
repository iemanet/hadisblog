package com.emanet.blog.repository;

import com.emanet.blog.model.CommentRmzlhds;
import com.emanet.blog.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRmzlhdsRepository extends JpaRepository<CommentRmzlhds, Long> {

    Page<CommentRmzlhds> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

}
