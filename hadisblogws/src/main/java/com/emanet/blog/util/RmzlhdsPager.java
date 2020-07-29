package com.emanet.blog.util;

import com.emanet.blog.model.Rmzlhds;

import org.springframework.data.domain.Page;

/**
 * I_EMANET
 * 
 */
public class RmzlhdsPager {

    private final Page<Rmzlhds> rmzlhdsList;

    public RmzlhdsPager(Page<Rmzlhds> rmzlhdsList) {
        this.rmzlhdsList = rmzlhdsList;
    }

    public int getPageIndex() {
        return rmzlhdsList.getNumber() + 1;
    }

    public int getPageSize() {
        return rmzlhdsList.getSize();
    }

    public boolean hasNext() {
        return rmzlhdsList.hasNext();
    }

    public boolean hasPrevious() {
        return rmzlhdsList.hasPrevious();
    }

    public int getTotalPages() {
        return rmzlhdsList.getTotalPages();
    }

    public long getTotalElements() {
        return rmzlhdsList.getTotalElements();
    }

    public Page<Rmzlhds> getRmzlhdsList() {
        return rmzlhdsList;
    }

    public boolean indexOutOfBounds() {
        return getPageIndex() < 0 || getPageIndex() > getTotalElements();
    }

}
