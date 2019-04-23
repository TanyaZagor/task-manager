package ru.zagorodnikova.tm.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.faces.bean.RequestScoped;
import java.util.List;

@Getter
@Setter
@Component
//@ManagedBean
@RequestScoped
//@URLMapping(id="projectList", pattern="/projectList", viewId="/WEB-INF/views/projectList.xhtml")
public class ProjectListController {

    @Autowired
    private IProjectService projectService;

    private String userId = "49a929db-0877-4aee-85e8-cd1ded1986f4";

    public List<Project> projectList() {
        List<Project> projects = projectService.findAll(userId);
        return projects;
    }

}
