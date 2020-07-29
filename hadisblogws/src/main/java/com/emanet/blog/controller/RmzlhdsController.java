package com.emanet.blog.controller;

import com.emanet.blog.model.Rmzlhds;
import com.emanet.blog.service.RmzlhdsService;
import com.emanet.blog.util.RmzlhdsPager;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RmzlhdsController {

    private final RmzlhdsService rmzlhdsService;

    @Autowired
    public RmzlhdsController(RmzlhdsService rmzlhdsService) {
        this.rmzlhdsService = rmzlhdsService;
    }

    @GetMapping("/rmzlhds")
    public String rmzlhds(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<Rmzlhds> rmzlhdsList = rmzlhdsService.findAll(page);
        RmzlhdsPager rmzlhdsPager = new RmzlhdsPager(rmzlhdsList);

        model.addAttribute("rmzlhdsPager", rmzlhdsPager);

        return "/rmzlhds";
    }

    @RequestMapping(value = "/rmzlhds/{id}", method = RequestMethod.GET)
    public String getRmzlhdsById(@PathVariable Long id, Principal principal, Model model) {

        Optional<Rmzlhds> optionalRmzlhds = rmzlhdsService.findById(id);

        if (optionalRmzlhds.isPresent()) {

            Rmzlhds rmzlhds = optionalRmzlhds.get();
            model.addAttribute("rmzlhds", rmzlhds);

            if (principal != null) {
                model.addAttribute("username", principal.getName());
            }

            return "/rmzlhdsDetail";

        } else {
            return "/error";
        }
    }
}
