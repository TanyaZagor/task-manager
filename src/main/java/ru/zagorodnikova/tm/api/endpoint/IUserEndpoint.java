package ru.zagorodnikova.tm.api.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint {

    @Nullable
    @WebMethod
    User signIn(@WebParam(name = "login") @Nullable final String login,
                @WebParam(name = "password") @Nullable final String password) throws Exception;

    @Nullable
    @WebMethod
    User signUp(@WebParam(name = "login") @Nullable final String login,
                @WebParam(name = "password") @Nullable final String password,
                @WebParam(name = "firstName") @Nullable final String fistName,
                @WebParam(name = "lastName") @Nullable final String lastName,
                @WebParam(name = "email") @Nullable final String email);
}
