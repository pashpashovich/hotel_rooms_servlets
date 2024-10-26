package ru.clevertec.hotelbooking.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.clevertec.hotelbooking.entity.Booking;
import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.repository.BookingRepository;
import ru.clevertec.hotelbooking.repository.BookingRepositoryImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/bookings")
public class BookingServlet extends HttpServlet {
    private final BookingRepository bookingRepository = new BookingRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            long customerId = ((User) session.getAttribute("user")).getId();
            List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/WEB-INF/views/user/bookings.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            long customerId = ((User) session.getAttribute("user")).getId();
            long roomId = Long.valueOf(request.getParameter("roomId"));
            System.out.println(roomId);
            Booking booking = new Booking(customerId, roomId);
            bookingRepository.save(booking);
            response.sendRedirect(request.getContextPath() + "/user/bookings");
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}

