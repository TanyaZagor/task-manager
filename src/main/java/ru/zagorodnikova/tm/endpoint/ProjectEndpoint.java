package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Autowired
    private IProjectService projectService;

    @Override
    @Nullable
    public Project persistProject(@NotNull String userId, @Nullable String name, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish) throws Exception {
        return projectService.persist(userId, name, description, dateStart, dateFinish);
    }

    @Override
    public void removeProject(@NotNull String projectId) {
        projectService.remove(projectId);
    }

    @Override
    @Nullable
    public List<Project> findAllProjects(@NotNull String userId) {
        return projectService.findAll(userId);
    }

    @Override
    @Nullable
    public Project findOneProject(@NotNull String projectId) {
        return projectService.findOne(projectId);
    }

    @Override
    public Project mergeProject(@NotNull String projectId, @NotNull String name, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish, @NotNull String status) throws Exception {
        return projectService.merge(projectId, name, description, dateStart, dateFinish, status);
    }
}
