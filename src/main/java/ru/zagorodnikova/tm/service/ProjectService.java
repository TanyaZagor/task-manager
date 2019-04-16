package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.List;

@Component
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public @Nullable Project persist(@NotNull String userId,
                                     @Nullable String name,
                                     @Nullable String description,
                                     @Nullable String dateStart,
                                     @Nullable String dateFinish) throws Exception {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        if (dateStart == null || dateStart.isEmpty()) return null;
        if (dateFinish == null || dateFinish.isEmpty()) return null;
        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        project.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
        project.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        return projectRepository.persist(project);
    }

    @Override
    public void remove(@NotNull String id) {
        Project project = projectRepository.findOne(id);
        taskRepository.removeAllInProject(project.getUserId());
        projectRepository.remove(id);
    }

    @Override
    public @Nullable List<Project> findAll(@NotNull String userId) {
        return projectRepository.findAll(userId);
    }

    @Override
    public @Nullable Project findOne(@NotNull String id) {
        return projectRepository.findOne(id);
    }

    @Nullable
    @Override
    public Project merge(@NotNull String id,
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
        Project project = projectRepository.findOne(id);
        assert project != null;
        project.setName(name);
        project.setDescription(description);
        project.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
        project.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        Status newStatus = Status.createStatus(status);
        project.setStatus(newStatus);
        projectRepository.merge(project);
        return project;
    }
}
