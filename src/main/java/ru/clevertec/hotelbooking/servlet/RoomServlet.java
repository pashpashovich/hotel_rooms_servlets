package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.service.RoomService;
import ru.clevertec.hotelbooking.repository.RoomRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/admin/rooms")
public class RoomServlet extends HttpServlet {
    private final RoomService roomService;

    public RoomServlet() {
        this.roomService = new RoomService(new RoomRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("rooms", roomService.getAllRooms());
        request.getRequestDispatcher("/WEB-INF/views/admin/rooms.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        roomService.addRoom(roomType, price);
        response.sendRedirect(request.getContextPath() + "/admin/rooms");
    }
}
