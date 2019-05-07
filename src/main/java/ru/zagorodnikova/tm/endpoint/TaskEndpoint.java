package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Task;

import javax.jws.WebService;
import java.util.List;

@Service
@WebService(endpointInterface = "ru.zagorodnikova.tm.api.endpoint.ITaskEndpoint")
public class TaskEndpoint implements ITaskEndpoint {

    @Autowired
    private ITaskService taskService;

    @Override
    @Nullable
    public Task persistTask(@NotNull String projectId, @NotNull String taskName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish) throws Exception {
        return taskService.persist(projectId, taskName, description, dateStart, dateFinish);
    }

    @Override
    public void removeTask(@NotNull String taskId) {
        taskService.remove(taskId);
    }

    @Override
    public void removeAllTasksInProject(@NotNull String projectId) {
        taskService.removeAllInProject(projectId);
    }

    @Override
    @Nullable
    public Task mergeTask(@NotNull String taskId, @NotNull String name, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish, @NotNull String status) throws Exception {
        return taskService.merge(taskId, name, description, dateStart, dateFinish, status);
    }

    @Override
    @Nullable
    public List<Task> findAllTasksInProject(@NotNull String projectId) {
        return taskService.findAllInProject(projectId);
    }

    @Override
    @Nullable
    public Task findOneTask(@NotNull String taskId) {
        return taskService.findOne(taskId);
    }
}
