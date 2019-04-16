package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserService {
    @Nullable
    User signIn(@Nullable final String login,
                @Nullable final String password) throws Exception;

    @Nullable
    User signUp(@Nullable final String login,
                @Nullable final String password,
                @Nullable final String fistName,
                @Nullable final String lastName,
                @Nullable final String email);

    @Nullable
    User findOne(@Nullable final String userId);

    @Nullable
    List<User> getUsers();
}
