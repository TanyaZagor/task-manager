package ru.zagorodnikova.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import ru.zagorodnikova.tm.api.service.ITaskService;
import ru.zagorodnikova.tm.entity.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean
@RequestScoped
@URLMappings(mappings = {
        @URLMapping(id="taskEdit", pattern="/taskEdit", viewId="/WEB-INF/views/taskEdit.xhtml"),
        @URLMapping(id="taskList", pattern="/taskList", viewId="/WEB-INF/views/taskList.xhtml"),
        @URLMapping(id= "taskCreate", pattern = "/taskCreate", viewId = "/WEB-INF/views/taskCreate.xhtml")
})
public class TaskController {

    private String projectId;

    private String id;

    private String name;

    private String description;

    private String dateStart;

    private String dateFinish;

    private String status;

    private Task task;

    private List<Task> tasks;

    @ManagedProperty("#{taskService}")
    private ITaskService taskService;

    public void setTasksList() {
        tasks = taskService.findAllInProject(projectId);
    }

    public void getOneTask() {
        task = taskService.findOne(id);
    }

    public String editPage() {
        return "taskCreate?faces-redirect=true&includeViewParams=true";
    }

    public String update() throws Exception {
        taskService.merge(id, name, description, dateStart, dateFinish, status);
        return "taskList?faces-redirect=true&includeViewParams=true";
    }

    public String create() throws Exception {
        taskService.persist(projectId, name, description, dateStart, dateFinish);
        return "taskList?faces-redirect=true&includeViewParams=true";
    }

    public String remove(String id) {
        taskService.remove(id);
        return "taskList?faces-redirect=true&includeViewParams=true";
    }
}
