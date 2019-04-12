package ru.zagorodnikova.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/project-remove")
public class ProjectRemoveServlet extends HttpServlet {

    @NotNull private final IProjectRepository projectRepository = ProjectRepository.getInstance();
    @NotNull private final ITaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        taskRepository.removeAllInProject(req.getParameter("id"));
        projectRepository.remove(req.getParameter("id"));
        resp.sendRedirect("project-list");
    }
}
