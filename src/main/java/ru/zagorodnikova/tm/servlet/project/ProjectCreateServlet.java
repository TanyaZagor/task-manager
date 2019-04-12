package ru.zagorodnikova.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/project-create")
public class ProjectCreateServlet extends HttpServlet {

    @NotNull final private IProjectRepository projectRepository = ProjectRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/projectCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = new Project();
        project.setUserId((String) req.getSession().getAttribute("userId"));
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        try {
            project.setDateStart(DateFormatterUtil.dateFormatter(req.getParameter("dateStart")));
            project.setDateFinish(DateFormatterUtil.dateFormatter(req.getParameter("dateFinish")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projectRepository.persist(project);
        resp.sendRedirect("project-list");
    }
}
