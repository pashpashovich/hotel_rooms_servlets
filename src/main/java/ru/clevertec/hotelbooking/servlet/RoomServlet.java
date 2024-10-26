package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.entity.Room;
import ru.clevertec.hotelbooking.repository.RoomRepository;
import ru.clevertec.hotelbooking.repository.RoomRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/admin/rooms")
public class RoomServlet extends HttpServlet {
    private final RoomRepository roomRepository = new RoomRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomRepository.findAll();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/WEB-INF/views/admin/rooms.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        Room room = new Room(null, roomType, price);
        roomRepository.save(room);
        response.sendRedirect(request.getContextPath() + "/admin/rooms");
    }
}

