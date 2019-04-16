package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

public interface ITaskService {
    @Nullable
    Task persist(@NotNull final String projectId,
                 @Nullable final String name,
                 @Nullable final String description,
                 @Nullable final String dateStart,
                 @Nullable final String dateFinish) throws Exception;

    void removeAllInProject(@NotNull String projectId);

    void remove(@NotNull final String id);

    @Nullable
    Task merge(@NotNull final String id,
               @Nullable final String name,
               @Nullable final String description,
               @Nullable final String dateStart,
               @Nullable final String dateFinish,
               @Nullable final String status) throws Exception;

    @Nullable
    List<Task> findAllInProject(@NotNull final String projectId);

    @Nullable
    Task findOne(@NotNull final String id);

}
