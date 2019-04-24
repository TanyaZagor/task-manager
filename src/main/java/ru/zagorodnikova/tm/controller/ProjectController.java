package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import ru.zagorodnikova.tm.api.service.IProjectService;
import ru.zagorodnikova.tm.entity.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ManagedBean
@SessionScoped
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

    public List<Project> projectList(String userId) {
        List<Project> projects = projectService.findAll(userId);
        return projects;
    }

    public String editGet(){
        return "projectEdit?faces-redirect=true";
    }

    public Project projectGet() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        project = projectService.findOne(params.get("projectId"));
        return project;
    }

    public String update() throws Exception {
        projectService.merge(id, name, description, dateStart, dateFinish, status);
        return "projectList?faces-redirect=true";
    }

    public String create(String userId) throws Exception {
        projectService.persist(userId, name, description, dateStart, dateFinish);
        return "projectList?faces-redirect=true";
    }

    public String remove(String id) {
        projectService.remove(id);
        return "projectList?faces-redirect=true";
    }
}
