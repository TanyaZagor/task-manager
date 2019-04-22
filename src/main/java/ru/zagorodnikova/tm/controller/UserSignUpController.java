package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@Getter
@Setter
@Component
@ManagedBean
@RequestScoped
@URLMapping(id="userSignUp", pattern="/userSignUp", viewId="/WEB-INF/views/userSignUp.xhtml")
public class UserSignUpController {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @Autowired
    private IUserService userService;

    public String signUp() {
        User user = userService.signUp(login, password, firstName, lastName, email);
        if (user != null) {
            return "projectList";
        } else {
            return "userSignUp";
        }
    }
}
