package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Override
    @Nullable
    @Transactional
    public Task persist(@NotNull String projectId,
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
    @Transactional
    public void removeAllInProject(@NotNull String projectId) {
        taskRepository.removeAllInProject(projectId);
    }

    @Override
    @Transactional
    public void remove(@NotNull String id) {
        final Task task = findOne(id);
        if (task == null) return;
        taskRepository.remove(task);
    }

    @Nullable
    @Override
    @Transactional
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
        Task task = findOne(id);
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
    @Nullable
    @Transactional
    public List<Task> findAllInProject(@NotNull String projectId) {
        try {
            return taskRepository.findAllInProject(projectId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Nullable
    @Transactional
    public Task findOne(@NotNull String id) {
        try {
            return taskRepository.findOne(id);
        } catch (NoResultException e) {
            return null;
        }
    }
}
