package ru.clevertec.hotelbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String url = "jdbc:postgresql://localhost:5432/HOTEL_BOOKING";
    private static final String username = "postgres";
    private static final String password = "";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так....");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Тут не работает");
            e.printStackTrace();
        }
        return null;
    }
}
