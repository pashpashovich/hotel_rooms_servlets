package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.clevertec.hotelbooking.dto.BookingDTO;
import ru.clevertec.hotelbooking.dto.RoomDTO;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.repository.BookingRepositoryImpl;
import ru.clevertec.hotelbooking.repository.RoomRepositoryImpl;
import ru.clevertec.hotelbooking.service.BookingService;
import ru.clevertec.hotelbooking.service.RoomService;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/bookings")
public class BookingServlet extends HttpServlet {
    private final BookingService bookingService = new BookingService(new BookingRepositoryImpl());
    private final RoomService roomService = new RoomService(new RoomRepositoryImpl());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            long customerId = ((User) session.getAttribute("user")).getId();
            List<BookingDTO> bookings = bookingService.getBookingsByCustomerId(customerId);
            List<RoomDTO> availableRooms = roomService.getAvailableRooms();
            request.setAttribute("bookings", bookings);
            request.setAttribute("availableRooms", availableRooms);
            request.getRequestDispatcher("/WEB-INF/views/user/bookings.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            long customerId = ((User) session.getAttribute("user")).getId();
            long roomId = Long.parseLong(request.getParameter("roomId"));
            bookingService.createBooking(customerId, roomId);
            response.sendRedirect(request.getContextPath() + "/user/bookings");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
