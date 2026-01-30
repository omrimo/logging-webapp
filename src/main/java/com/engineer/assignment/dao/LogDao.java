package com.engineer.assignment.dao;



import com.engineer.assignment.db.DBUtil;
import com.engineer.assignment.model.LogMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LogDao {


    public LogDao() {
        initSchema();
    }


    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS LOG_MESSAGES (" +
                "ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "TIMESTAMP BIGINT," +
                "APP VARCHAR(100)," +
                "MESSAGE VARCHAR(1000))";


        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert(LogMessage log) {
        String sql = "INSERT INTO LOG_MESSAGES (TIMESTAMP, APP, MESSAGE) VALUES (?, ?, ?)";


        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, log.getTimestamp());
            ps.setString(2, log.getApp());
            ps.setString(3, log.getMessage());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<LogMessage> findAll() {
        List<LogMessage> logs = new ArrayList<>();
        String sql = "SELECT TIMESTAMP, APP, MESSAGE FROM LOG_MESSAGES ORDER BY ID DESC";


        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {


            while (rs.next()) {
                logs.add(new LogMessage(
                        rs.getLong("TIMESTAMP"),
                        rs.getString("APP"),
                        rs.getString("MESSAGE")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return logs;
    }


    public void clear() {
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            s.execute("DELETE FROM LOG_MESSAGES");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}