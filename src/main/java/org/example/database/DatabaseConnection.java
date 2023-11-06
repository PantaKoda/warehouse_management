package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String URL = "jdbc:sqlite:warehouseManagementDB.db";

    private DatabaseConnection(){
        try{
            this.connection = DriverManager.getConnection(URL);
            System.out.println("Connected to database");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

public static DatabaseConnection getInstance(){
        if (instance ==null){
            instance = new DatabaseConnection();
        }
        return instance;
}

public Connection getConnection(){
        return connection;
}


}
