/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.*;

/**
 *
 * @author Ognjen
 */
public class DbConnection {

    private final Connection connection;
    private final String url = "jdbc:mysql://localhost/prvikolokvijum2025";
    private final String user = "root";
    private final String password = "";
    private static DbConnection instance;

    public DbConnection() throws Exception {
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Konektovan!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Greska pri konekciji sa bazom! " + e.getMessage());
        }
    }

    public static DbConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
