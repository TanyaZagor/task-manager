package ru.zagorodnikova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull Task task);

    void remove(@NotNull String id);

    void removeAllInProject(@NotNull String projectId);

    void removeAll();

    @Nullable
    Task findOne(@NotNull String id);

    void merge(@NotNull Task task);

    @Nullable
    List<Task> findAllInProject(@NotNull String projectId);

    @Nullable
    List<Task> findAll();
}
