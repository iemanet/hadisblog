package com.emanet.blog.controller;

import com.emanet.blog.model.Rmzlhds;
import com.emanet.blog.service.RmzlhdsService;
import com.emanet.blog.util.RmzlhdsPager;
import com.emanet.blog.util.Searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchHadisController {

    private final RmzlhdsService rmzlhdsService;

    @Autowired
    public SearchHadisController(RmzlhdsService rmzlhdsService) {
        this.rmzlhdsService = rmzlhdsService;
    }

    @RequestMapping(value = "/searchHadis", method = RequestMethod.GET)
    public String searchHadisAll(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<Rmzlhds> rmzlhdsList = rmzlhdsService.findAll(page);
        RmzlhdsPager rmzlhdsPager = new RmzlhdsPager(rmzlhdsList);

        model.addAttribute("rmzlhdsPager", rmzlhdsPager);
        model.addAttribute("searcher", new Searcher());

        return "/searchHadis";
    }

    @RequestMapping(value = "/searchHadis", params = {"searchByKeyword"}, method = RequestMethod.POST)
    public String searchHadisByKeyword(@RequestParam(defaultValue = "0") int page, Model model,
        @ModelAttribute Searcher searcher) {

        Page<Rmzlhds> rmzlhdsList =
            rmzlhdsService.findByBodyIgnoreCaseContainingOrSubjectIgnoreCaseContainingOrderByIdAsc(
                searcher.getKeyword(), searcher.getKeyword(), page);

        RmzlhdsPager rmzlhdsPager = new RmzlhdsPager(rmzlhdsList);

        model.addAttribute("rmzlhdsPager", rmzlhdsPager);

        return "/searchHadis";
    }

    @RequestMapping(value = "/searchHadis", params = {"searchByPageId"}, method = RequestMethod.POST)
    public String searchHadisByPageId(@RequestParam(defaultValue = "0") int page, Model model,
        @ModelAttribute Searcher searcher) {

        Page<Rmzlhds> rmzlhdsList = rmzlhdsService.findByPageId(Long.valueOf(searcher.getPageId()), page);

        RmzlhdsPager rmzlhdsPager = new RmzlhdsPager(rmzlhdsList);

        model.addAttribute("rmzlhdsPager", rmzlhdsPager);

        return "/searchHadis";

    }

}
