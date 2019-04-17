package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.api.service.IUserService;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@NoArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Nullable
    @Transactional
    public User signIn(@Nullable String login, @Nullable String password) throws Exception {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        @NotNull final String passwordHash = PasswordUtil.hashPassword(password);
        try {
            return userRepository.signIn(login, passwordHash);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Nullable
    @Transactional
    public User signUp(@Nullable String login, @Nullable String password, @Nullable String firstName,
                                 @Nullable String lastName, @Nullable String email) {
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        if (firstName == null || firstName.isEmpty()) return null;
        if (lastName == null || lastName.isEmpty()) return null;
        if (email == null || email.isEmpty()) return null;
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
        return userRepository.signUp(user);
    }

    @Override
    @Nullable
    @Transactional
    public User findOne(@NotNull final String id) {
        try {
            return userRepository.findOne(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Nullable
    @Transactional
    public List<User> getUsers() {
        try {
            return userRepository.getUsers();
        } catch (NoResultException e) {
            return null;
        }
    }
}
