package ru.zagorodnikova.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.api.repository.IUserRepository;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.repository.UserRepository;
import ru.zagorodnikova.tm.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-signIn")
public class UserSignInServlet extends HttpServlet {

    @NotNull
    private final IUserRepository userRepository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/userSignIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            @NotNull final String password = PasswordUtil.hashPassword(req.getParameter("password"));
            User user = userRepository.signIn(req.getParameter("login"), password);
            if (user != null) {
                req.getSession().setAttribute("userId", user.getId());
                resp.sendRedirect("project-list");
            } else {
                resp.sendRedirect("user-signIn");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
