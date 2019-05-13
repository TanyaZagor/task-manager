package ru.zagorodnikova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zagorodnikova.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Nullable
    User save(@NotNull final User user);

    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.login = :login AND user.password = :password")
    User signIn(@NotNull @Param("login") final String login,
                @NotNull @Param("password") final String password);

    @Nullable
    List<User> findAll();

    Optional<User> findById(@NotNull final String id);

    @Nullable
    @Query(value = "SELECT user FROM User user WHERE user.login = :login")
    User findByLogin(@NotNull @Param("login") final String login);
}
