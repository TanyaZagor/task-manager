package ru.zagorodnikova.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.text.ParseException;

@Controller
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/task-list")
    public String taskList(@RequestParam String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("tasks", taskRepository.findAllInProject(projectId));
        return "taskList";
    }

    @GetMapping("/task-create")
    public String createTaskGet(@RequestParam String projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "taskCreate";
    }

    @PostMapping("/task-create")
    public String createTaskPost(@RequestParam String userId, @RequestParam String projectId, @RequestParam String name,
                                 @RequestParam String description, @RequestParam String dateStart,
                                 @RequestParam String dateFinish, Model model) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        task.setName(name);
        task.setDescription(description);
        try {
            task.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
            task.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taskRepository.persist(task);
        model.addAttribute("projectId", projectId);
        return "redirect:task-list";
    }

    @GetMapping("/task-remove")
    public String remove(@RequestParam String id, Model model) {
        Task task = taskRepository.findOne(id);
        taskRepository.remove(id);
        model.addAttribute("projectId", task.getProjectId());
        return "redirect:task-list";
    }

    @GetMapping("/task-update")
    public String updateTaskGet(@RequestParam String id, Model model) {
        model.addAttribute("task", taskRepository.findOne(id));
        return "taskEdit";
    }

    @PostMapping("/task-update")
    public String updateTaskPost(@RequestParam String id, @RequestParam String name, @RequestParam String description,
                                 @RequestParam String dateStart, @RequestParam String dateFinish, @RequestParam String status,
                                 Model model) {
        Task task = taskRepository.findOne(id);
        task.setName(name);
        task.setDescription(description);
        try {
            task.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
            task.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status newStatus = Status.createStatus(status);
        task.setStatus(newStatus);
        taskRepository.merge(task);
        model.addAttribute("projectId", task.getProjectId());
        return "redirect:task-list";
    }
}
