package com.emanet.blog.util;

import com.emanet.blog.model.Post;
import org.springframework.data.domain.Page;

/**
 * I_EMANET
 * 
 */
public class Pager {

    private final Page<Post> postList;

    public Pager(Page<Post> postList) {
        this.postList = postList;
    }

    public int getPageIndex() {
        return postList.getNumber() + 1;
    }

    public int getPageSize() {
        return postList.getSize();
    }

    public boolean hasNext() {
        return postList.hasNext();
    }

    public boolean hasPrevious() {
        return postList.hasPrevious();
    }

    public int getTotalPages() {
        return postList.getTotalPages();
    }

    public long getTotalElements() {
        return postList.getTotalElements();
    }

    public Page<Post> getPostList() {
        return postList;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
