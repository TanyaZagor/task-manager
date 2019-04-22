package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Getter
@Setter
@Component
@ManagedBean
@SessionScoped
@URLMapping(id="userSignIn", pattern="/userSignIn", viewId="/WEB-INF/views/userSignIn.xhtml")
public class UserSignInController {

    private String password;

    private String login;

    @Autowired
    private IUserService userService;

    public String signIn() throws Exception {
        System.out.println(login);
        User user = userService.signIn(login, password);
        if (user != null) {
            return "projectList";
        } else {
            return "userSignIn";
        }
    }
}
