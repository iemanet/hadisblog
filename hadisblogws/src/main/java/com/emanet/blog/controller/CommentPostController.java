package com.emanet.blog.controller;

import com.emanet.blog.model.CommentPost;
import com.emanet.blog.model.Post;
import com.emanet.blog.model.User;
import com.emanet.blog.service.CommentPostService;
import com.emanet.blog.service.PostService;
import com.emanet.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentPostController {

    private final PostService postService;

    private final UserService userService;

    private final CommentPostService commentPostService;

    @Autowired
    public CommentPostController(PostService postService, UserService userService,
        CommentPostService commentPostService) {
        this.postService = postService;
        this.userService = userService;
        this.commentPostService = commentPostService;
    }

    @RequestMapping(value = "/newCommentPost/{id}", method = RequestMethod.GET)
    public String newCommentPost(@PathVariable Long id, Principal principal, Model model) {

        Optional<Post> post = postService.findById(id);

        if (post.isPresent()) {
            Optional<User> user = userService.findByUsername(principal.getName());

            if (user.isPresent()) {
                CommentPost commentPost = new CommentPost();
                commentPost.setUser(user.get());
                commentPost.setPost(post.get());

                model.addAttribute("commentPost", commentPost);

                return "/editCommentPostForm";

            } else {
                return "/error";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/editCommentPost", method = RequestMethod.POST)
    public String editCommentPost(@Valid CommentPost commentPost, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/editCommentPostForm";
        } else {
            commentPostService.save(commentPost);
            return "redirect:/post/" + commentPost.getPost().getId();
        }
    }

    @RequestMapping(value = "/editCommentPost/{id}", method = RequestMethod.GET)
    public String editCommentPostById(@PathVariable Long id, Principal principal, Model model) {

        Optional<CommentPost> optionalCommentPost = commentPostService.findById(id);

        if (optionalCommentPost.isPresent()) {
            CommentPost commentPost = optionalCommentPost.get();

            if (isPrincipalOwner(principal, commentPost)) {
                model.addAttribute("commentPost", commentPost);
                return "/editCommentPostForm";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/deleteCommentPost/{id}", method = RequestMethod.DELETE)
    public String deleteCommentPostById(@PathVariable Long id, Principal principal) {

        Optional<CommentPost> optionalCommentPost = commentPostService.findById(id);

        if (optionalCommentPost.isPresent()) {
            CommentPost commentPost = optionalCommentPost.get();

            if (isPrincipalOwner(principal, commentPost)) {
                commentPostService.delete(commentPost);
                return "redirect:/post/" + commentPost.getPost().getId();
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    private boolean isPrincipalOwner(Principal principal, CommentPost commentPost) {
        return principal != null && principal.getName().equals(commentPost.getUser().getUsername());
    }

}
