package ru.zagorodnikova.tm.entity.enumeration;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    ADMIN("admin"),
    USER("user");

    @NotNull
    private final String displayName;

    RoleType(@NotNull final String display) {
        this.displayName = display;
    }

    @NotNull
    @Override
    public String toString() {
        return this.displayName;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }
}