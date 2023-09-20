package org.example.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;
    private static DatabaseConnection instance;
    private final String url = "jdbc:postgresql://localhost/dvdrental";
    private final String user = "postgres";
    private final String password = "root";
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected");
            this.connection = DriverManager.getConnection(url,user,password);
        } catch (Exception ex) {
            System.out.println("Not Connected");
            System.out.println(ex);
            this.connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
