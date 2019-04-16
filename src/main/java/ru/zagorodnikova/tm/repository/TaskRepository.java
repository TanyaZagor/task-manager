package ru.zagorodnikova.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class TaskRepository implements ITaskRepository {
    @NotNull
    private final Map<String, Task> tasks = new LinkedHashMap<>();


    @Nullable
    @Override
    public Task persist(@NotNull Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void remove(@NotNull String id) {
        tasks.remove(id);
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(projectId));
    }

    @Override
    public void removeAll() {
        tasks.clear();
    }

    @Nullable
    @Override
    public Task findOne(@NotNull String id) {
        return tasks.get(id);
    }

    @Override
    public void merge(@NotNull Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public @Nullable List<Task> findAllInProject(@NotNull String projectId) {
        @Nullable final List<Task> list = new LinkedList<>();
        tasks.forEach((k,v) -> {
            if (v.getProjectId().equals(projectId)) list.add(v);
        });
        return list;
    }

    @Override
    public @Nullable List findAll() {
        @Nullable final List<Task> list = new LinkedList<>();
        tasks.forEach((k,v) -> list.add(v));
        return list;
    }

}
