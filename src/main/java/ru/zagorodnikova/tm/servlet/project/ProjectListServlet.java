package ru.zagorodnikova.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.repository.ProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project-list")
public class ProjectListServlet extends HttpServlet {

    @NotNull
    private final IProjectRepository projectRepository = ProjectRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projects", projectRepository.findAll((String) req.getSession().getAttribute("userId")));
        req.getRequestDispatcher("/WEB-INF/views/projectList.jsp").forward(req,resp);
    }
}
