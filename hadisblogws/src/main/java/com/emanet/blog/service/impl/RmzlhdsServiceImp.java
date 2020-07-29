package com.emanet.blog.service.impl;

import com.emanet.blog.model.Rmzlhds;
import com.emanet.blog.repository.RmzlhdsRepository;
import com.emanet.blog.service.RmzlhdsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RmzlhdsServiceImp implements RmzlhdsService {

    private final int RECORD_PER_PAGE = 10;

    private final RmzlhdsRepository rmzlhdsRepository;

    @Autowired
    public RmzlhdsServiceImp(RmzlhdsRepository rmzlhdsRepository) {
        this.rmzlhdsRepository = rmzlhdsRepository;
    }

    @Override
    public Optional<Rmzlhds> findById(Long id) {
        return rmzlhdsRepository.findById(id);
    }

    @Override
    public Page<Rmzlhds> findByBodyIgnoreCaseContainingOrSubjectIgnoreCaseContainingOrderByIdAsc(String body,
        String subject, int page) {
        return rmzlhdsRepository.findByBodyIgnoreCaseContainingOrSubjectIgnoreCaseContainingOrderByIdAsc(body, subject,
            PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    @Override
    public Page<Rmzlhds> findByPageId(Long pageId, int page) {
        return rmzlhdsRepository.findByPageId(pageId, PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    @Override
    public Page<Rmzlhds> findAll(int page) {
        return rmzlhdsRepository.findAll(PageRequest.of(subtractPageByOne(page), RECORD_PER_PAGE));
    }

    private int subtractPageByOne(int page) {
        return (page < 1) ? 0 : page - 1;
    }

}
