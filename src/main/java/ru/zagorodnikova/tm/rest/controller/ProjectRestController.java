package ru.zagorodnikova.tm.rest.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import java.util.List;

@RestController
@RequestMapping("projects-rest")
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @Nullable
    @PostMapping(value = "/create", produces = "application/json")
    public Project createPost(@RequestParam String userId, @RequestParam String name, @RequestParam String description,
                                 @RequestParam String dateStart, @RequestParam String dateFinish) throws Exception {
        return projectService.persist(userId, name, description, dateStart, dateFinish);
    }

    @DeleteMapping(value = "/remove")
    public void removeProject(@NotNull @RequestParam String projectId) {
        projectService.remove(projectId);
    }

    @Nullable
    @GetMapping(value = "/findAll", produces = "application/json")
    public List<Project> findAllProjects(@RequestParam @NotNull String userId) {
        return projectService.findAll(userId);
    }

    @Nullable
    @GetMapping(value = "/findOne", produces = "application/json")
    public Project findOneProject(@RequestParam @NotNull String projectId) {
        return projectService.findOne(projectId);
    }

    @Nullable
    @PutMapping(value = "/merge", produces = "application/json")
    public Project mergeProject(@RequestParam @NotNull String projectId,
                                @RequestParam @NotNull String name,
                                @RequestParam @NotNull String description,
                                @RequestParam @NotNull String dateStart,
                                @RequestParam @NotNull String dateFinish,
                                @RequestParam @NotNull String status) throws Exception {
        return projectService.merge(projectId, name, description, dateStart, dateFinish, status);
    }
}
