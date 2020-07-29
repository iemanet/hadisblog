package com.emanet.blog.controller;

import com.emanet.blog.model.Post;
import com.emanet.blog.service.PostService;
import com.emanet.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<Post> postList = postService.findAllByOrderByCreateDateDesc(page);
        Pager pager = new Pager(postList);

        model.addAttribute("pager", pager);

        return "/home";
    }
}
