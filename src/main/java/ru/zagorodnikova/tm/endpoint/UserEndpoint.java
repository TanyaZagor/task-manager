package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zagorodnikova.tm.api.endpoint.IUserEndpoint;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.zagorodnikova.tm.api.endpoint.IUserEndpoint")
public class UserEndpoint implements IUserEndpoint {

    @Autowired
    private IUserService userService;

    @Override
    @Nullable
    public User signIn(@WebParam(name = "login") @Nullable final String login,
                       @WebParam(name = "password") @Nullable final String password) throws Exception {
        return userService.signIn(login, password);
    }

    @Override
    @Nullable
    public User signUp(@Nullable String login, @Nullable String password, @Nullable String fistName, @Nullable String lastName, @Nullable String email) {
        return userService.signUp(login, password, fistName, lastName, email);
    }
}
