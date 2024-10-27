package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Room;
import ru.clevertec.hotelbooking.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomRepositoryImpl implements RoomRepository {
    private static final Logger logger = Logger.getLogger(RoomRepositoryImpl.class.getName());


    @Override
    public void save(Room room) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "INSERT INTO rooms (room_type, price) VALUES (?, ?)";
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, room.getRoomType());
                stmt.setBigDecimal(2, room.getPrice());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка сохранения комнаты: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "DELETE FROM rooms WHERE id = ?";
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка удаления комнаты: " + e.getMessage());
        }
    }


    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "SELECT id, room_type, price FROM rooms";
            assert connection != null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setId(rs.getLong("id"));
                    room.setRoomType(rs.getString("room_type"));
                    room.setPrice(rs.getBigDecimal("price"));
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка нахождения комнат: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> findAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "SELECT rooms.id,room_type, price FROM rooms LEFT JOIN bookings ON rooms.id = room_id WHERE room_id IS NULL";
            assert connection != null;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setId(rs.getLong("id"));
                    room.setRoomType(rs.getString("room_type"));
                    room.setPrice(rs.getBigDecimal("price"));
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e, () -> "Ошибка нахождения комнат: " + e.getMessage());
        }
        return rooms;
    }
}
