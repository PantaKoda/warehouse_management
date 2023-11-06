package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    private Connection connection;

    public TableManager(Connection conn) {
        this.connection = conn;
    }


    public void createTables() {
        createProductsTable();
        createInventoryTable();
        createWarehousesTable();
    }

    private void executeSql(String sql) {

        try (Statement stm = connection.createStatement()) {
            stm.execute(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createProductsTable() {

        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "productId TEXT PRIMARY KEY," +
                "productName TEXT NOT NULL," +
                "productPrice REAL NOT NULL," +
                "productDescription TEXT," +
                "productLength REAL," +
                "productWidth REAL," +
                "productWeight REAL," +
                "productCreationTimestampUTC TEXT NOT NULL" +
                ");";
        executeSql(sql);
    }


    private void createWarehousesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (" +
                "warehouseId TEXT PRIMARY KEY," +
                "warehouseName TEXT NOT NULL," +
                "warehouseAddress TEXT," +
                "warehouseZipcode TEXT," +
                "warehouseCity TEXT," +
                "warehouseCountry TEXT" +
                ");";

        executeSql(sql);
    }

    private void createInventoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS inventory (" +
                "productID TEXT," +
                "warehouseId TEXT," +
                "productQuantity INTEGER," +
                "productQuantityUpdateTimestampUTC TEXT NOT NULL," +
                "productVersion INTEGER," +
                "PRIMARY KEY (productID, warehouseId)," +
                "FOREIGN KEY (productID) REFERENCES products(productId)," +
                "FOREIGN KEY (warehouseId) REFERENCES warehouses(warehouseId)" +
                ");";

        executeSql(sql);
    }

}
