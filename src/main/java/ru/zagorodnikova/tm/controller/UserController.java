package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@Getter
@Setter
@ManagedBean
@RequestScoped
@URLMappings(mappings = {
        @URLMapping(id="userSignIn", pattern="/userSignIn", viewId="/WEB-INF/views/userSignIn.xhtml"),
        @URLMapping(id="userSignUp", pattern="/userSignUp", viewId="/WEB-INF/views/userSignUp.xhtml")
})
public class UserController {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String userId;

    @ManagedProperty("#{userService}")
    private IUserService userService;

    public String signUp() {
        User user = userService.signUp(login, password, firstName, lastName, email);
        if (user != null) {
            userId = user.getId();
            return "userSignIn?faces-redirect=true";
        } else {
            return "userSignUp";
        }
    }

    public String signOut() {
        userId = null;
        return "userSignIn?faces-redirect=true";
    }
}
