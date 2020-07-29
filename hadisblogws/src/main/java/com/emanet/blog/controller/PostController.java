package com.emanet.blog.controller;

import com.emanet.blog.model.Post;
import com.emanet.blog.model.User;
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
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String newPost(Principal principal, Model model) {

        Optional<User> user = userService.findByUsername(principal.getName());

        if (user.isPresent()) {
            Post post = new Post();
            post.setUser(user.get());

            model.addAttribute("post", post);

            return "/editPostForm";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public String editPost(@Valid Post post, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/editPostForm";
        } else {
            postService.save(post);
            return "redirect:/blog/" + post.getUser().getUsername();
        }
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostById(@PathVariable Long id, Principal principal, Model model) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwner(principal, post)) {
                model.addAttribute("post", post);
                return "/editPostForm";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostById(@PathVariable Long id, Principal principal, Model model) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {

            Post post = optionalPost.get();
            model.addAttribute("post", post);

            if (principal != null) {
                model.addAttribute("username", principal.getName());
            }

            return "/postDetail";

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
    public String deletePostById(@PathVariable Long id, Principal principal) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwner(principal, post)) {
                postService.delete(post);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    private boolean isPrincipalOwner(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
}
