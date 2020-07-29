package com.emanet.blog.util;

import org.springframework.data.domain.Page;

import com.emanet.blog.model.CommentRmzlhds;

/**
 * I_EMANET
 * 
 */
public class RmzlhdsCommentPager {

    private final Page<CommentRmzlhds> rmzlhdsCommentList;

    public RmzlhdsCommentPager(Page<CommentRmzlhds> rmzlhdsCommentList) {
        this.rmzlhdsCommentList = rmzlhdsCommentList;
    }

    public int getPageIndex() {
        return rmzlhdsCommentList.getNumber() + 1;
    }

    public int getPageSize() {
        return rmzlhdsCommentList.getSize();
    }

    public boolean hasNext() {
        return rmzlhdsCommentList.hasNext();
    }

    public boolean hasPrevious() {
        return rmzlhdsCommentList.hasPrevious();
    }

    public int getTotalPages() {
        return rmzlhdsCommentList.getTotalPages();
    }

    public long getTotalElements() {
        return rmzlhdsCommentList.getTotalElements();
    }

    public Page<CommentRmzlhds> getRmzlhdsCommentList() {
        return rmzlhdsCommentList;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
