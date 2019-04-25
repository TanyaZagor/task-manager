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

    @ManagedProperty("#{param.taskId}")
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

    public String setTasks(String projectId) {
        tasks = taskService.findAllInProject(projectId);
        return "taskList?faces-redirect=true";
    }

    public String editGet(Task task){
        return "taskEdit?faces-redirect=true";
    }

    public String update() throws Exception {
        taskService.merge(id, name, description, dateStart, dateFinish, status);
        return "taskList?faces-redirect=true";
    }

    public String create(String projectId) throws Exception {
        taskService.persist(projectId, name, description, dateStart, dateFinish);
        return "taskList?faces-redirect=true";
    }

    public String remove(String id) {
        taskService.remove(id);
        return "taskList?faces-redirect=true";
    }
}
