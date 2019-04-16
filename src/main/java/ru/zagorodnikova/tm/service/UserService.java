package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.PasswordUtil;

import java.util.List;

@Component
@NoArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public @Nullable User signIn(@Nullable String login, @Nullable String password) throws Exception {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        @NotNull final String passwordHash = PasswordUtil.hashPassword(password);
        User user = userRepository.signIn(login, passwordHash);
        return user;
    }

    @Override
    public @Nullable User signUp(@Nullable String login, @Nullable String password, @Nullable String firstName,
                                 @Nullable String lastName, @Nullable String email) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        if (firstName == null || firstName.isEmpty()) return null;
        if (lastName == null || lastName.isEmpty()) return null;
        if (email == null || email.isEmpty()) return null;
        if (userRepository.findOneByLogin(login) != null) return null;
        User user = new User();
        user.setLogin(login);
        try {
            user.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.signUp(user);
        return null;
    }

    @Override
    public @Nullable User findOne(@Nullable String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.findOneByLogin(login);
    }

    @Override
    public @Nullable List<User> getUsers() {
        return userRepository.getUsers();
    }
}
