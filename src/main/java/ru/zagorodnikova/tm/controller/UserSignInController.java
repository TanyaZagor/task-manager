package ru.zagorodnikova.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.faces.bean.SessionScoped;

@Getter
@Setter
//@ManagedBean
@Component
@SessionScoped
//@URLMapping(id="userSignIn", pattern="/userSignIn", viewId="/WEB-INF/views/userSignIn.xhtml")
public class UserSignInController {

    private String password;

    private String login;

    @Autowired
    private IUserService userService;

    public String signIn() throws Exception {
        User user = userService.signIn(login, password);
        if (user != null) {
            return "projectList";
        } else {
            return "userSignIn";
        }
    }
}
