package ru.zagorodnikova.tm.repository;


import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.entity.Project;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ProjectRepository implements IProjectRepository {

    private static ProjectRepository instance = null;

    @NotNull
    private final Map<String, Project> projects = new LinkedHashMap<>();

    private ProjectRepository() {
    }

    public static ProjectRepository getInstance() {
        if (instance == null) {
            instance = new ProjectRepository();
        }
        return instance;
    }

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
