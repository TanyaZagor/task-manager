package ru.zagorodnikova.tm.servlet.task;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.ITaskRepository;
import ru.zagorodnikova.tm.entity.Task;
import ru.zagorodnikova.tm.entity.enumeration.Status;
import ru.zagorodnikova.tm.repository.TaskRepository;
import ru.zagorodnikova.tm.util.DateFormatterUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/task-edit")
public class TaskUpdateServlet extends HttpServlet {

    @NotNull
    private final ITaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskRepository.findOne(req.getParameter("id"));
        req.setAttribute("task", task);
        req.getRequestDispatcher("/WEB-INF/views/taskEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task task = taskRepository.findOne(req.getParameter("id"));
        task.setName(req.getParameter("name"));
        task.setDescription(req.getParameter("description"));
        try {
            task.setDateStart(DateFormatterUtil.dateFormatter(req.getParameter("dateStart")));
            task.setDateFinish(DateFormatterUtil.dateFormatter(req.getParameter("dateFinish")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status status = Status.createStatus(req.getParameter("status"));
        task.setStatus(status);
        taskRepository.merge(task);
        resp.sendRedirect("task-list?projectId=" + task.getProjectId());
    }
}
