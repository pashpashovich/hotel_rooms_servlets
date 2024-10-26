package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Booking;
import ru.clevertec.hotelbooking.util.DBUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepository {

    public void save(Booking booking) {
        String sql = "INSERT INTO bookings (customer_id, room_id,booking_date) VALUES (?, ?,?)";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, booking.getCustomerId());
            pstmt.setLong(2, booking.getRoomId());
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> findByCustomerId(long customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_id = ?";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getLong("customer_id"),
                        rs.getLong("room_id")
                );
                booking.setId(rs.getLong("id"));
                booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}

