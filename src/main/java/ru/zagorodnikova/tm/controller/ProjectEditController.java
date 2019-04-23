package ru.zagorodnikova.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.faces.bean.SessionScoped;

@Getter
@Setter
//@ManagedBean
@Component
@SessionScoped
//@URLMapping(id="projectEdit", pattern="/projectEdit", viewId="/WEB-INF/views/projectEdit.xhtml")
public class ProjectEditController {

    private String id;

    private String name;

    private String description;

    private String dateStart;

    private String dateFinish;

    private String status;

    private Project project;

    @Autowired
    private IProjectService projectService;

    public String editGet(Project project){
        System.out.println(project.getName());
        this.project = project;
        return "projectEdit";
    }

    public void projectSet(Project project) {
        System.out.println(project.getName());
        this.project = project;
    }

    public String update() throws Exception {
        Project project = projectService.merge(id, name, description, dateStart, dateFinish, status);
        return "projectList";
    }
}
