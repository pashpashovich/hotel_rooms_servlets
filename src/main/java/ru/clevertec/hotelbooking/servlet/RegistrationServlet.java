package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.dto.UserDTO;
import ru.clevertec.hotelbooking.repository.UserRepository;
import ru.clevertec.hotelbooking.service.UserService;
import ru.clevertec.hotelbooking.util.Role;

import java.io.IOException;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService;

    public RegistrationServlet() {
        this.userService = new UserService(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Role role = Role.USER;
        UserDTO userDto = new UserDTO(username, password, role);
        String registrationResult = userService.registerUser(userDto);
        if ("Логин уже существует".equals(registrationResult)) {
            request.setAttribute("error", "username_taken");
            request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login?success=registered");
        }
    }
}
