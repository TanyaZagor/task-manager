package ru.zagorodnikova.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.dto.ProjectDto;
import ru.zagorodnikova.tm.entity.Project;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@WebService(endpointInterface = "ru.zagorodnikova.tm.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @Autowired
    private IProjectService projectService;

    @Override
    @Nullable
    public ProjectDto persistProject(@NotNull String userId, @Nullable String name, @Nullable String description, @Nullable String dateStart, @Nullable String dateFinish) throws Exception {
        @Nullable final Project project = projectService.persist(userId, name, description, dateStart, dateFinish);
        if (project == null) return null;
        return new ProjectDto(project);
    }

    @Override
    public void removeProject(@NotNull String projectId) {
        projectService.remove(projectId);
    }

    @Override
    @Nullable
    public List<ProjectDto> findAllProjects(@NotNull String userId) {
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.findAll(userId);
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @Override
    @Nullable
    public ProjectDto findOneProject(@NotNull String projectId) {
        @Nullable final Project project = projectService.findOne(projectId);
        if (project == null) return null;
        return new ProjectDto(project);
    }

    @Override
    public ProjectDto mergeProject(@NotNull String projectId, @NotNull String name, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish, @NotNull String status) throws Exception {
        @Nullable final Project project = projectService.merge(projectId, name, description, dateStart, dateFinish, status);
        if (project == null) return null;
        return new ProjectDto(project);
    }
}
