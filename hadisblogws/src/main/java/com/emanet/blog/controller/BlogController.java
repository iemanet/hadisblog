package com.emanet.blog.controller;

import com.emanet.blog.model.CommentPost;
import com.emanet.blog.model.CommentRmzlhds;
import com.emanet.blog.model.Post;
import com.emanet.blog.model.User;
import com.emanet.blog.service.CommentPostService;
import com.emanet.blog.service.CommentRmzlhdsService;
import com.emanet.blog.service.PostService;
import com.emanet.blog.service.UserService;
import com.emanet.blog.util.Pager;
import com.emanet.blog.util.PostCommentPager;
import com.emanet.blog.util.RmzlhdsCommentPager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {

    private final UserService userService;

    private final PostService postService;

    private final CommentPostService commentPostService;

    private final CommentRmzlhdsService commentRmzlhdsService;

    @Autowired
    public BlogController(UserService userService, PostService postService, CommentPostService commentPostService,
        CommentRmzlhdsService commentRmzlhdsService) {

        this.userService = userService;
        this.postService = postService;
        this.commentPostService = commentPostService;
        this.commentRmzlhdsService = commentRmzlhdsService;
    }

    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public String blogByUsername(@PathVariable String username, @RequestParam(defaultValue = "0") int page,
        Model model) {

        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<Post> postList = postService.findByUserOrderByCreateDateDesc(user, page);
            Pager pager = new Pager(postList);

            model.addAttribute("pager", pager);
            model.addAttribute("user", user);

            return "/post";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/postComment/{username}", method = RequestMethod.GET)
    public String postCommentByUsername(@PathVariable String username, @RequestParam(defaultValue = "0") int page,
        Model model) {

        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<CommentPost> postCommentList = commentPostService.findByUserOrderByCreateDateDesc(user, page);
            PostCommentPager postCommentPager = new PostCommentPager(postCommentList);

            model.addAttribute("postCommentPager", postCommentPager);
            model.addAttribute("user", user);

            return "/postComment";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/rmzlhdsComment/{username}", method = RequestMethod.GET)
    public String rmzlhdsCommentByUsername(@PathVariable String username, @RequestParam(defaultValue = "0") int page,
        Model model) {

        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<CommentRmzlhds> rmzlhdsCommentList = commentRmzlhdsService.findByUserOrderByCreateDateDesc(user, page);
            RmzlhdsCommentPager rmzlhdsCommentPager = new RmzlhdsCommentPager(rmzlhdsCommentList);

            model.addAttribute("rmzlhdsCommentPager", rmzlhdsCommentPager);
            model.addAttribute("user", user);

            return "/rmzlhdsComment";

        } else {
            return "/error";
        }
    }
}
