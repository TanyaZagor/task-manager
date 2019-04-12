package ru.zagorodnikova.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    @NotNull
    private final ITaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = taskRepository.findOne(req.getParameter("id")).getProjectId();
        taskRepository.remove(req.getParameter("id"));
        resp.sendRedirect("task-list?projectId=" + projectId);
    }
}
