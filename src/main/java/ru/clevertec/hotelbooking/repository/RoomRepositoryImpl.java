package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.Room;
import ru.clevertec.hotelbooking.util.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public void save(Room room) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "INSERT INTO rooms (room_type, price) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, room.getRoomType());
                stmt.setBigDecimal(2, room.getPrice());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room room) {
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "DELETE FROM rooms WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Room> findById(Long id) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection()) {
            String sql = "SELECT * FROM rooms";
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
            e.printStackTrace();
        }
        return rooms;
    }
}
