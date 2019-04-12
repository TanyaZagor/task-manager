package ru.zagorodnikova.tm.servlet.project;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IProjectRepository;
import ru.zagorodnikova.tm.entity.Project;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repository.ProjectRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/project-edit")
public class ProjectUpdateServlet extends HttpServlet {
    @NotNull
    private final IProjectRepository projectRepository = ProjectRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = projectRepository.findOne(req.getParameter("id"));
        req.setAttribute("project", project);
        req.getRequestDispatcher("/WEB-INF/views/projectEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Project project = projectRepository.findOne(req.getParameter("id"));
        project.setName(req.getParameter("name"));
        project.setDescription(req.getParameter("description"));
        try {
            project.setDateStart(DateFormatterUtil.dateFormatter(req.getParameter("dateStart")));
            project.setDateFinish(DateFormatterUtil.dateFormatter(req.getParameter("dateFinish")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status status = Status.createStatus(req.getParameter("status"));
        project.setStatus(status);
        projectRepository.merge(project);
        resp.sendRedirect("project-list");
    }
}
