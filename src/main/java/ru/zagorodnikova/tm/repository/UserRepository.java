package ru.zagorodnikova.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@NoArgsConstructor
public class UserRepository implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public User signUp(@NotNull final User user){
        entityManager.persist(user);
        return user;
    }

    @Nullable
    @Override
    public User signIn(@NotNull String login, @NotNull String password) {
        return entityManager.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    @Nullable
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class)
                .getResultList();
    }

    @Override
    @Nullable
    public User findOne(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }
}
