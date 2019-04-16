package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    @Nullable
    User signUp(@NotNull final User user);

    @Nullable
    User signIn(@NotNull final String login, @NotNull String password);

    @Nullable
    User findOneByLogin(@NotNull final String login);

    @Nullable
    List<User> getUsers();
}
