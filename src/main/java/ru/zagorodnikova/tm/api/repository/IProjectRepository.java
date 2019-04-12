package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectRepository {

    @Nullable
    Project persist(@NotNull final Project project);

    void remove(@NotNull final String id);

    void removeAll();

    @Nullable
    Project findOne(@NotNull final String id);

    void merge(@NotNull final Project project);

    @Nullable
    List<Project> findAll(@NotNull final String userId);

    @Nullable
    List<Project> getProjects();
}
