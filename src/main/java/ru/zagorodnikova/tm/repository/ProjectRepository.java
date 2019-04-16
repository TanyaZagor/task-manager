package ru.zagorodnikova.tm.repository;


import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Component
@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {

    @NotNull
    private final Map<String, Project> projects = new LinkedHashMap<>();

    @Nullable
    @Override
    public Project persist(@NotNull final Project project) {
        projects.put(project.getId(), project);
        return project;
    }

    @Override
    public void remove(@NotNull final String id) {
        projects.remove(id);
    }

    @Override
    public void removeAll() {
        projects.clear();
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id) {
        return projects.get(id);
    }

    @Override
    public void merge(@NotNull final Project project) {
        projects.put(project.getId(), project);
    }

    @Override
    public @Nullable List<Project> findAll(@NotNull final String userId) {
        @Nullable final List<Project> list = new LinkedList<>();
        projects.forEach((k,v) -> {
            if (v.getUserId().equals(userId)) list.add(v);
        });
        return list;
    }

    @Override
    public @Nullable List<Project> getProjects() {
        @Nullable final List<Project> list = new LinkedList<>();
        projects.forEach((k,v) -> list.add(v));
        return list;
    }
}
