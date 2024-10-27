package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.dto.UserDTO;
import ru.clevertec.hotelbooking.repository.UserRepository;
import ru.clevertec.hotelbooking.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class UserManagementServlet extends HttpServlet {
    private final UserService userService;

    public UserManagementServlet() {
        this.userService = new UserService(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDTO> users = userService.getAllNonAdminUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/admin/user_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        if ("delete".equals(action)) {
            userService.deleteUser(username);
        } else if ("makeAdmin".equals(action)) {
            userService.makeUserAdmin(username);
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
