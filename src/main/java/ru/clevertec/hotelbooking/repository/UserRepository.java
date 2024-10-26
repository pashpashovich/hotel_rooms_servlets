package ru.clevertec.hotelbooking.repository;

import ru.clevertec.hotelbooking.entity.User;
import ru.clevertec.hotelbooking.util.DBUtils;
import ru.clevertec.hotelbooking.util.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<User> {

    @Override
    public Optional<User> findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRole(long userId, String newRole) {
        String sql = "UPDATE users SET role = ? WHERE id = ?";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newRole);
            stmt.setLong(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(User user) {
        String query;
        if (user.getId() == null) {
            query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        } else {
            query = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        }
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());

            if (user.getId() != null) {
                statement.setLong(4, user.getId());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findNotAdmins() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = DBUtils.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), Role.valueOf(rs.getString("role"))));
            }
            users = users.stream()
                    .filter(user -> user.getRole().equals(Role.USER))
                    .toList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
