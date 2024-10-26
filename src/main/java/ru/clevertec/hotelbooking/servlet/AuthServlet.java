package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.repository.UserRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.equals(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (user.getRole().toString().equals("ADMIN")) response.sendRedirect(request.getContextPath() + "/profile");
                else response.sendRedirect(request.getContextPath() + "/user");
            } else {
                request.setAttribute("error", "password_not_correct");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "username_not_found");
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}

