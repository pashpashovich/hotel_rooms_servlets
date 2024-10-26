package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.hotelbooking.repository.RoomRepository;
import ru.clevertec.hotelbooking.repository.RoomRepositoryImpl;

import java.io.IOException;

@WebServlet("/admin/rooms/delete/*")
public class RoomDeleteServlet extends HttpServlet {

    private final RoomRepository roomRepository = new RoomRepositoryImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String roomIdString = pathInfo.substring(1);
            int roomId = Integer.parseInt(roomIdString);
            roomRepository.delete((long) roomId);
            response.sendRedirect(request.getContextPath() + "/admin/rooms");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid room ID");
        }
    }
}

