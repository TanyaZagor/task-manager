package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean
@Component
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id="projectEdit", pattern="/projectEdit", viewId="/WEB-INF/views/projectEdit.xhtml"),
        @URLMapping(id="projectList", pattern="/projectList", viewId="/WEB-INF/views/projectList.xhtml")
})
public class ProjectController {

    private String id;

    private String name;

    private String description;

    private String dateStart;

    private String dateFinish;

    private String status;

    private Project project;

    @Autowired
    private IProjectService projectService;

    private String userId = "49a929db-0877-4aee-85e8-cd1ded1986f4";

    public List<Project> projectList() {
        List<Project> projects = projectService.findAll(userId);
        return projects;
    }

    public String editGet(Project project){
        System.out.println(project.getName());
        this.project = project;
        return "projectEdit";
    }

    public String update() throws Exception {
        Project project = projectService.merge(id, name, description, dateStart, dateFinish, status);
        return "projectList";
    }
}
