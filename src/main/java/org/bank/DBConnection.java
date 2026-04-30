package org.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:D:\\JavaTrain\\Customer.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}