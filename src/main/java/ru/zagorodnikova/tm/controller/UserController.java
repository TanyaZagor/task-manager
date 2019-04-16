package ru.zagorodnikova.tm.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

@Controller
public class UserController {

    @Autowired
    private ServiceLocator bootstrap;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/user-signIn")
    public String signInGet() {
        try {
            if (userRepository.getUsers().isEmpty()) bootstrap.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userSignIn";
    }

    @PostMapping("/user-signIn")
    public String signInPost(@RequestParam String login, @RequestParam String password, Model model) {
        try {
            @NotNull final String passwordHash = PasswordUtil.hashPassword(password);
            User user = userRepository.signIn(login, passwordHash);
            if (user != null) {
                model.addAttribute("userId", user.getId());
                return "redirect:project-list";
            } else {
                return "userSignIn";
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (userRepository.findOneByLogin(login) == null) {
            User user = new User();
            user.setLogin(login);
            try {
                user.setPassword(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            userRepository.signUp(user);
            model.addAttribute("userId", user.getId());
            return "redirect:project-list";
        } else {
            return "userSignUp";
        }
    }
}
