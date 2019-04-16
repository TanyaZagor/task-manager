package ru.zagorodnikova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

public interface IProjectService {
    @Nullable
    Project persist(@NotNull final String userId,
                           @Nullable final String name,
                           @Nullable final String description,
                           @Nullable final String dateStart,
                           @Nullable final String dateFinish) throws Exception;

    void remove(@NotNull final String id);

    @Nullable
    List<Project> findAll(@NotNull final String userId);

    @Nullable
    Project findOne(@NotNull final String id);

    @Nullable
    Project merge(@NotNull final String id,
                      @Nullable final String name,
                      @Nullable final String description,
                      @Nullable final String dateStart,
                      @Nullable final String dateFinish,
                      @Nullable final String status) throws Exception;
}
