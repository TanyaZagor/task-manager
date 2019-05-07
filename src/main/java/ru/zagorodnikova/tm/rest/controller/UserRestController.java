package ru.zagorodnikova.tm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

@RestController
@RequestMapping("/users-rest")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/user-signIn", produces = "application/json")
    public User signInPost(@RequestParam("login") String login, @RequestParam("password") String password) throws Exception {
        return userService.signIn(login, password);
    }

    @PostMapping("/user-signUp")
    public User signUpPost(@RequestParam String login, @RequestParam String password, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam String email) {
        return userService.signUp(login, password, firstName, lastName, email);
    }
}
