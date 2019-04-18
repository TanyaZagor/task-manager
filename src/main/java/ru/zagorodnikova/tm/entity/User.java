package ru.zagorodnikova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Cacheable
@NoArgsConstructor
@Table(name = "app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();

    @Column(unique = true)
    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType roleType = RoleType.USER;

    public User(@Nullable String login, @Nullable String password, @Nullable String firstName,
                @Nullable String lastName, @Nullable String email) throws Exception {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if (password != null) {
            this.password = PasswordUtil.hashPassword(password);
        }
    }

    public void setPassword(@NotNull String password) throws Exception {
        this.password = PasswordUtil.hashPassword(password);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
