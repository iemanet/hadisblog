package com.emanet.blog.util;

import org.springframework.data.domain.Page;

import com.emanet.blog.model.CommentPost;

/**
 * I_EMANET
 * 
 */
public class PostCommentPager {

    private final Page<CommentPost> postCommentList;

    public PostCommentPager(Page<CommentPost> postCommentList) {
        this.postCommentList = postCommentList;
    }

    public int getPageIndex() {
        return postCommentList.getNumber() + 1;
    }

    public int getPageSize() {
        return postCommentList.getSize();
    }

    public boolean hasNext() {
        return postCommentList.hasNext();
    }

    public boolean hasPrevious() {
        return postCommentList.hasPrevious();
    }

    public int getTotalPages() {
        return postCommentList.getTotalPages();
    }

    public long getTotalElements() {
        return postCommentList.getTotalElements();
    }

    public Page<CommentPost> getPostCommentList() {
        return postCommentList;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
