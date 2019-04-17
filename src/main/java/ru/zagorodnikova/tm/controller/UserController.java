package ru.zagorodnikova.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user-signIn")
    public String signInGet(){
        return "userSignIn";
    }

    @PostMapping("/user-signIn")
    public String signInPost(@RequestParam String login, @RequestParam String password, Model model) throws Exception {
        User user = userService.signIn(login, password);
        if (user != null) {
            model.addAttribute("userId", user.getId());
            return "redirect:project-list";
        } else {
            return "userSignIn";
        }
    }

    @GetMapping("/user-signOut")
    public String signOut() {
        return "home";
    }

    @GetMapping("/user-signUp")
    public String signUpGet() {
        return "userSignUp";
    }

    @PostMapping("/user-signUp")
    public String signUpPost(@RequestParam String login, @RequestParam String password, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam String email, Model model) {
        User user = userService.signUp(login, password, firstName, lastName, email);
        if (user != null) {
            model.addAttribute("userId", user.getId());
            return "redirect:project-list";
        } else {
            return "userSignUp";
        }
    }
}
