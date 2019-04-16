package ru.zagorodnikova.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Task;

@Controller
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("/task-list")
    public String taskList(@RequestParam String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("tasks", taskService.findAllInProject(projectId));
        return "taskList";
    }

    @GetMapping("/task-create")
    public String createTaskGet(@RequestParam String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "taskCreate";
    }

    @PostMapping("/task-create")
    public String createTaskPost( @RequestParam String projectId, @RequestParam String name,
                                 @RequestParam String description, @RequestParam String dateStart,
                                 @RequestParam String dateFinish, Model model) throws Exception {
        taskService.persist(projectId, name, description, dateStart, dateFinish);
        model.addAttribute("projectId", projectId);
        return "redirect:task-list";
    }

    @GetMapping("/task-remove")
    public String remove(@RequestParam String id, Model model) {
        Task task = taskService.findOne(id);
        taskService.remove(id);
        model.addAttribute("projectId", task.getProjectId());
        return "redirect:task-list";
    }

    @GetMapping("/task-update")
    public String updateTaskGet(@RequestParam String id, Model model) {
        model.addAttribute("task", taskService.findOne(id));
        return "taskEdit";
    }

    @PostMapping("/task-update")
    public String updateTaskPost(@RequestParam String id, @RequestParam String name, @RequestParam String description,
                                 @RequestParam String dateStart, @RequestParam String dateFinish, @RequestParam String status,
                                 Model model) throws Exception {
        Task task = taskService.merge(id, name, description, dateStart, dateFinish, status);
        model.addAttribute("projectId", task.getProjectId());
        return "redirect:task-list";
    }
}
