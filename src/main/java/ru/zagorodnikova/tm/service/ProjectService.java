package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.util.List;

@Service
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Nullable
    @Transactional
    public Project persist(@NotNull String userId,
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
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void remove(@NotNull String id) {
        Project project = findOne(id);
        if (project == null) return;
        taskRepository.removeAllInProject(project.getUserId());
        projectRepository.delete(project);
    }

    @Override
    @Nullable
    @Transactional
    public List<Project> findAll(@NotNull String userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    @Nullable
    @Transactional
    public Project findOne(@NotNull String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Nullable
    @Override
    @Transactional
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
        Project project = findOne(id);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        project.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
        project.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        Status newStatus = Status.createStatus(status);
        project.setStatus(newStatus);
        projectRepository.save(project);
        return project;
    }
}
