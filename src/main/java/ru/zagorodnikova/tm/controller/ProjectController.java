package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.CustomUserDetails;
import ru.zagorodnikova.tm.entity.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean
@RequestScoped
@URLMappings(mappings = {
        @URLMapping(id="projectEdit", pattern="/projectEdit", viewId="/WEB-INF/views/projectEdit.xhtml"),
        @URLMapping(id="projectList", pattern="/projectList", viewId="/WEB-INF/views/projectList.xhtml"),
        @URLMapping(id="projectCreate", pattern="/projectCreate", viewId="/WEB-INF/views/projectCreate.xhtml")
})
public class ProjectController {

    private String id;

    private String name;

    private String description;

    private String dateStart;

    private String dateFinish;

    private String status;

    private Project project;

    @ManagedProperty("#{projectService}")
    private IProjectService projectService;

    public List<Project> projectList() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Project> projects = projectService.findAll(userDetails.getUser().getId());
        return projects;
    }

    public void getOneProject() {
        project = projectService.findOne(id);
    }

    public String update() throws Exception {
        projectService.merge(id, name, description, dateStart, dateFinish, "done");
        return "projectList?faces-redirect=true";
    }

    public String create() throws Exception {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getId());
        projectService.persist(userDetails.getUser().getId(), name, description, dateStart, dateFinish);
        return "projectList?faces-redirect=true";
    }

    public String remove(String id) {
        projectService.remove(id);
        return "projectList?faces-redirect=true";
    }

}
