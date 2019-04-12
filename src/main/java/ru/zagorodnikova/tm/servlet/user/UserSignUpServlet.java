package ru.zagorodnikova.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-signUp")
public class UserSignUpServlet extends HttpServlet {

    @NotNull private final IUserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/userSignUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userRepository.findOneByLogin(req.getParameter("login")) != null) {
            resp.sendRedirect("user-signUp");
        } else {
            User user = new User();
            user.setLogin(req.getParameter("login"));
            try {
                user.setPassword(req.getParameter("password"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            userRepository.signUp(user);
            req.getSession().setAttribute("userId", user.getId());
            resp.sendRedirect("project-list");
        }
    }
}
