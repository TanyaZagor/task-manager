package ru.zagorodnikova.tm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @GetMapping("/project-list")
    public String projectList(@RequestParam String userId, Model model) {
        List<Project> projects = projectService.findAll(userId);
        model.addAttribute("projects", projects);
        model.addAttribute("userId", userId);
        return "projectList";
    }

    @GetMapping("project-remove")
    public String remove(@RequestParam String id, Model model) {
        Project project = projectService.findOne(id);
        projectService.remove(id);
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
                             @RequestParam String dateStart, @RequestParam String dateFinish, Model model) throws Exception {
        projectService.persist(userId, name, description, dateStart, dateFinish);
        model.addAttribute("userId", userId);
        return "redirect:project-list";
    }

    @GetMapping("/project-update")
    public String updateGet(@RequestParam String id, Model model){
        Project project = projectService.findOne(id);
        model.addAttribute("project", project);
        return "projectEdit";
    }

    @PostMapping("/project-update")
    public String updatePost(@RequestParam String id, @RequestParam String name, @RequestParam String description, @RequestParam String dateStart, @RequestParam String dateFinish, @RequestParam String status, Model model) throws Exception {
        Project project = projectService.merge(id, name, description, dateStart, dateFinish, status);
        model.addAttribute("userId", project.getUserId());
        return "redirect:project-list";
    }
}
