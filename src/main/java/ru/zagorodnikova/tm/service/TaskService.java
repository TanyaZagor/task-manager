package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.List;

@Component
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    public @Nullable Task persist(@NotNull String projectId,
                                  @Nullable String name,
                                  @Nullable String description,
                                  @Nullable String dateStart,
                                  @Nullable String dateFinish) throws Exception {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        if (dateStart == null || dateStart.isEmpty()) return null;
        if (dateFinish == null || dateFinish.isEmpty()) return null;
        Project project = projectRepository.findOne(projectId);
        Task task = new Task();
        task.setUserId(project.getUserId());
        task.setProjectId(projectId);
        task.setName(name);
        task.setDescription(description);
        task.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
        task.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        return taskRepository.persist(task);
    }

    @Override
    public void removeAllInProject(@NotNull String projectId) {
        taskRepository.removeAllInProject(projectId);
    }

    @Override
    public void remove(@NotNull String id) {
        taskRepository.remove(id);
    }

    @Nullable
    @Override
    public Task merge(@NotNull String id,
                      @Nullable String name,
                      @Nullable String description,
                      @Nullable String dateStart,
                      @Nullable String dateFinish,
                      @Nullable String status) throws Exception {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        if (dateStart == null || dateStart.isEmpty()) return null;
        if (dateFinish == null || dateFinish.isEmpty()) return null;
        if (status == null || status.isEmpty()) return null;
        Task task = taskRepository.findOne(id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        task.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
        task.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        Status newStatus = Status.createStatus(status);
        task.setStatus(newStatus);
        taskRepository.merge(task);
        return task;
    }

    @Override
    public @Nullable List<Task> findAllInProject(@NotNull String projectId) {
        return taskRepository.findAllInProject(projectId);
    }

    @Override
    public @Nullable Task findOne(@NotNull String id) {
        return taskRepository.findOne(id);
    }
}
