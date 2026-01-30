package com.engineer.assignment.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {


    private static final String JDBC_URL = "jdbc:h2:mem:logs;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";


    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}