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
public class DatabaseConnection {

    private final Connection connection;
    private final String url = "jdbc:mysql://127.0.0.1/2019prviKolokvijum";
    private final String user = "root";
    private final String password = "";

    private static DatabaseConnection instance;

    private DatabaseConnection() throws Exception {
        connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
    }

    public static DatabaseConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
