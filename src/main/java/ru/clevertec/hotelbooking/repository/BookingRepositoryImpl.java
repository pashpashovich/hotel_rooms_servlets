package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Booking;
import ru.clevertec.hotelbooking.util.DBUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingRepositoryImpl implements BookingRepository {
    private static final Logger logger = Logger.getLogger(BookingRepositoryImpl.class.getName());


    public void save(Booking booking) {
        String sql = "INSERT INTO bookings (customer_id, room_id,booking_date) VALUES (?, ?,?)";
        try (Connection connection = DBUtils.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, booking.getCustomerId());
                preparedStatement.setLong(2, booking.getRoomId());
                preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка сохранения бронирования: " + e.getMessage());
        }
    }

    public List<Booking> findByCustomerId(long customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id,customer_id, room_id,booking_date FROM bookings WHERE customer_id = ?";
        try (Connection connection = DBUtils.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, customerId);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Booking booking = new Booking(
                            rs.getLong("customer_id"),
                            rs.getLong("room_id")
                    );
                    booking.setId(rs.getLong("id"));
                    booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка нахождения бронирований: " + e.getMessage());
        }
        return bookings;
    }
}

