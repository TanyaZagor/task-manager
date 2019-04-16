package ru.zagorodnikova.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.User;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class UserRepository implements IUserRepository {

    @NotNull
    private final Map<String, User> users = new LinkedHashMap<>();

    @Nullable
    @Override
    public User signUp(@NotNull final User user){
        users.put(user.getId(), user);
        return user;
    }

    @Nullable
    @Override
    public User signIn(@NotNull String login, @NotNull String password) {
        final List<User> list = new LinkedList<>();
        users.forEach((k,v) -> {
            if (v.getLogin().equals(login) && v.getPassword().equals(password)) {
                list.add(v);
            }
        });
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Nullable
    @Override
    public User findOneByLogin(@NotNull String login) {
        final List<User> list = new LinkedList<>();
        users.forEach((k,v) -> {
            if (v.getLogin().equals(login)) {
                list.add(v);
            }
        });
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public @Nullable List<User> getUsers() {
        final List<User> list = new LinkedList<>();
        users.forEach((k,v) -> list.add(v));
        return list;
    }


}
