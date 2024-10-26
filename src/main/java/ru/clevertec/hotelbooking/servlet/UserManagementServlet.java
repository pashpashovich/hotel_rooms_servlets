package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.repository.Repository;
import ru.clevertec.hotelbooking.repository.UserRepository;
import ru.clevertec.hotelbooking.util.Role;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserManagementServlet extends HttpServlet {
    private final Repository userRepository = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userRepository.findNotAdmins();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/admin/user_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        long userId = Long.parseLong(request.getParameter("userId"));

        if ("delete".equals(action)) {
            userRepository.deleteById(userId);
        } else if ("makeAdmin".equals(action)) {
            userRepository.updateRole(userId, "ADMIN");
        }
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
