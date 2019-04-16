package ru.zagorodnikova.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import java.text.ParseException;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping("/project-list")
    public String projectList(@RequestParam String userId, Model model) {
        List<Project> projects = projectRepository.findAll(userId);
        model.addAttribute("projects", projects);
        model.addAttribute("userId", userId);
        return "projectList";
    }

    @GetMapping("project-remove")
    public String remove(@RequestParam String id, Model model) {
        Project project = projectRepository.findOne(id);
        taskRepository.removeAllInProject(project.getUserId());
        projectRepository.remove(id);
        model.addAttribute("userId", project.getUserId());
        return "redirect:project-list";
    }

    @GetMapping("/project-create")
    public String createGet(@RequestParam String userId, Model model){
        model.addAttribute("userId", userId);
        return "projectCreate";
    }

    @PostMapping("/project-create")
    public String createPost(@RequestParam String userId, @RequestParam String name, @RequestParam String description,
                             @RequestParam String dateStart, @RequestParam String dateFinish, Model model) {
        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        try {
            project.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
            project.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectRepository.persist(project);
        model.addAttribute("userId", project.getUserId());
        return "redirect:project-list";
    }

    @GetMapping("/project-update")
    public String updateGet(@RequestParam String id, Model model){
        Project project = projectRepository.findOne(id);
        model.addAttribute("project", project);
        return "projectEdit";
    }

    @PostMapping("/project-update")
    public String updatePost(@RequestParam String id, @RequestParam String name, @RequestParam String description, @RequestParam String dateStart, @RequestParam String dateFinish, @RequestParam String status, Model model){
        Project project = projectRepository.findOne(id);
        project.setName(name);
        project.setDescription(description);
        try {
            project.setDateStart(DateFormatterUtil.dateFormatter(dateStart));
            project.setDateFinish(DateFormatterUtil.dateFormatter(dateFinish));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status newStatus = Status.createStatus(status);
        project.setStatus(newStatus);
        projectRepository.merge(project);
        model.addAttribute("userId", project.getUserId());
        return "redirect:project-list";
    }
}
