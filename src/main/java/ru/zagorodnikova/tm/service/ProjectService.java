package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

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
        return projectRepository.persist(project);
    }

    @Override
    @Transactional
    public void remove(@NotNull String id) {
        Project project = findOne(id);
        if (project == null) return;
        taskRepository.removeAllInProject(project.getUserId());
        projectRepository.remove(project);
    }

    @Override
    @Nullable
    @Transactional
    public List<Project> findAll(@NotNull String userId) {
        try {
            return projectRepository.findAll(userId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Nullable
    @Transactional
    public Project findOne(@NotNull String id) {
        try {
            return projectRepository.findOne(id);
        } catch (NoResultException e) {
            return null;
        }
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
        projectRepository.merge(project);
        return project;
    }
}
