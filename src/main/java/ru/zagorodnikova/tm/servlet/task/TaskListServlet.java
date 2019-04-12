package ru.zagorodnikova.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task-list")
public class TaskListServlet extends HttpServlet {

    @NotNull final ITaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projectId",req.getParameter("projectId"));
        req.setAttribute("tasks", taskRepository.findAllInProject(req.getParameter("projectId")));
        req.getRequestDispatcher("/WEB-INF/views/taskList.jsp").forward(req,resp);
    }
}
