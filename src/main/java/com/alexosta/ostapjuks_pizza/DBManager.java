package com.alexosta.ostapjuks_pizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String USER = "postgresql";
    private static final String PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    // Методы для работы с базой данных, например, getProducts(), getCategories(), saveReceipt()
}
