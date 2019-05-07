package ru.zagorodnikova.tm.rest.controller;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Task;

import java.util.List;

@RestController
@RequestMapping("tasks-rest")
public class TaskRestController {

    @Autowired
    private ITaskService taskService;

    @Nullable
    @PostMapping(value = "/create", produces = "application/json")
    public Task persistTask(@RequestParam @NotNull String projectId,
                            @RequestParam @NotNull String taskName,
                            @RequestParam @NotNull String description,
                            @RequestParam @NotNull String dateStart,
                            @RequestParam @NotNull String dateFinish) throws Exception {
        return taskService.persist(projectId, taskName, description, dateStart, dateFinish);
    }

    @DeleteMapping(value = "/remove")
    public void removeTask(@RequestParam @NotNull String taskId) {
        taskService.remove(taskId);
    }

    @DeleteMapping(value = "/removeAllInProject")
    public void removeAllTasksInProject(@RequestParam @NotNull String projectId) {
        taskService.removeAllInProject(projectId);
    }

    @Nullable
    @PutMapping(value = "/merge", produces = "application/json")
    public Task mergeTask(@RequestParam @NotNull String taskId,
                          @RequestParam @NotNull String name,
                          @RequestParam @NotNull String description,
                          @RequestParam @NotNull String dateStart,
                          @RequestParam @NotNull String dateFinish,
                          @RequestParam @NotNull String status) throws Exception {
        return taskService.merge(taskId, name, description, dateStart, dateFinish, status);
    }

    @Nullable
    @GetMapping(value = "/findAll", produces = "application/json")
    public List<Task> findAllTasksInProject(@RequestParam @NotNull String projectId) {
        return taskService.findAllInProject(projectId);
    }

    @Nullable
    @GetMapping(value = "/findOne", produces = "application/json")
    public Task findOneTask(@RequestParam @NotNull String taskId) {
        return taskService.findOne(taskId);
    }
}
