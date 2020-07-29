package com.emanet.blog.controller;

import com.emanet.blog.model.CommentRmzlhds;
import com.emanet.blog.model.Rmzlhds;
import com.emanet.blog.model.User;
import com.emanet.blog.service.CommentRmzlhdsService;
import com.emanet.blog.service.RmzlhdsService;
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
public class CommentRmzlhdsController {

    private final RmzlhdsService rmzlhdsService;

    private final UserService userService;

    private final CommentRmzlhdsService commentRmzlhdsService;

    @Autowired
    public CommentRmzlhdsController(RmzlhdsService rmzlhdsService, UserService userService,
        CommentRmzlhdsService commentRmzlhdsService) {
        this.rmzlhdsService = rmzlhdsService;
        this.userService = userService;
        this.commentRmzlhdsService = commentRmzlhdsService;
    }

    @RequestMapping(value = "/editCommentRmzlhds", method = RequestMethod.POST)
    public String editCommentRmzlhds(@Valid CommentRmzlhds commentRmzlhds, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/editCommentRmzlhdsForm";
        } else {
            commentRmzlhdsService.save(commentRmzlhds);
            return "redirect:/rmzlhds/" + commentRmzlhds.getRmzlhds().getId();
        }
    }

    @RequestMapping(value = "/editCommentRmzlhds/{id}", method = RequestMethod.GET)
    public String editCommentRmzlhdsById(@PathVariable Long id, Principal principal, Model model) {

        Optional<CommentRmzlhds> optionalCommentRmzlhds = commentRmzlhdsService.findById(id);

        if (optionalCommentRmzlhds.isPresent()) {
            CommentRmzlhds commentRmzlhds = optionalCommentRmzlhds.get();

            if (isPrincipalOwner(principal, commentRmzlhds)) {
                model.addAttribute("commentRmzlhds", commentRmzlhds);
                return "/editCommentRmzlhdsForm";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/newCommentRmzlhds/{id}", method = RequestMethod.GET)
    public String getCommentRmzlhdsById(@PathVariable Long id, Principal principal, Model model) {

        Optional<Rmzlhds> rmzlhds = rmzlhdsService.findById(id);

        if (rmzlhds.isPresent()) {
            Optional<User> user = userService.findByUsername(principal.getName());

            if (user.isPresent()) {
                CommentRmzlhds commentRmzlhds = new CommentRmzlhds();
                commentRmzlhds.setUser(user.get());
                commentRmzlhds.setRmzlhds(rmzlhds.get());

                model.addAttribute("commentRmzlhds", commentRmzlhds);

                return "/editCommentRmzlhdsForm";

            } else {
                return "/error";
            }

        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/deleteCommentRmzlhds/{id}", method = RequestMethod.DELETE)
    public String deleteCommentRmzlhdsById(@PathVariable Long id, Principal principal) {

        Optional<CommentRmzlhds> optionalCommentRmzlhds = commentRmzlhdsService.findById(id);

        if (optionalCommentRmzlhds.isPresent()) {
            CommentRmzlhds commentRmzlhds = optionalCommentRmzlhds.get();

            if (isPrincipalOwner(principal, commentRmzlhds)) {
                commentRmzlhdsService.delete(commentRmzlhds);
                return "redirect:/rmzlhds/" + commentRmzlhds.getRmzlhds().getId();
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }

    private boolean isPrincipalOwner(Principal principal, CommentRmzlhds commentRmzlhds) {
        return principal != null && principal.getName().equals(commentRmzlhds.getUser().getUsername());
    }

}
