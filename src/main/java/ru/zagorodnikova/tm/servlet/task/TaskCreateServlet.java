package ru.zagorodnikova.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/task-create")
public class TaskCreateServlet extends HttpServlet {

    @NotNull
    final private ITaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projectId",req.getParameter("projectId"));
        req.getRequestDispatcher("/WEB-INF/views/taskCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = new Task();
        task.setUserId((String) req.getSession().getAttribute("userId"));
        task.setProjectId(req.getParameter("projectId"));
        task.setName(req.getParameter("name"));
        task.setDescription(req.getParameter("description"));
        try {
            task.setDateStart(DateFormatterUtil.dateFormatter(req.getParameter("dateStart")));
            task.setDateFinish(DateFormatterUtil.dateFormatter(req.getParameter("dateFinish")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taskRepository.persist(task);
        resp.sendRedirect("task-list?projectId=" + req.getParameter("projectId"));
    }
}
