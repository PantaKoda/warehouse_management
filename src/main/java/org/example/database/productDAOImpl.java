package org.example.database;

import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class productDAOImpl implements productDAO {

    private List<Product> products = new ArrayList<>();


    @Override
    public void saveToDB(Connection con, List<Product> products) throws SQLException {
        String sql = "INSERT INTO products (productId, productName, productPrice, productDescription, productLength, productWidth, productWeight, productCreationTimestampUTC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement pstm = con.prepareStatement(sql);) {

            for (Product product : products) {

                //Mandatory
                pstm.setString(1, product.getProductId());
                pstm.setString(2, product.getProductName());
                pstm.setDouble(3, product.getProductPrice());


                //optional
                if (product.getProductDescription() != null && !product.getProductDescription().isEmpty()) {
                    pstm.setString(4, product.getProductDescription());
                } else {
                    pstm.setNull(4, Types.VARCHAR);
                }

                if (product.getProductLength() > 0) {
                    pstm.setDouble(5, product.getProductLength());
                } else {
                    pstm.setNull(5, Types.DOUBLE);
                }

                if (product.getProductWidth() > 0) {
                    pstm.setDouble(6, product.getProductWidth());
                } else {
                    pstm.setNull(6, Types.DOUBLE);
                }

                if (product.getProductWeight() > 0) {
                    pstm.setDouble(7, product.getProductWeight());
                } else {
                    pstm.setNull(7, Types.DOUBLE);
                }

                pstm.setString(8, product.getProductCreationTimestampUTC());

                pstm.addBatch();
            }


            pstm.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error saving product: " + e.getMessage());
        }
    }


    @Override
    public void getAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM products";

        try (Connection con = conn;
             PreparedStatement pstm = con.prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                String productDetails = String.format("Product ID: %s, Name: %s, Price: %.2f, Description: %s, Length: %.2f, Width: %.2f, Weight: %.2f, CreatedTime(UTC): %s",
                        resultSet.getString("productId"),
                        resultSet.getString("productName"),
                        resultSet.getDouble("productPrice"),
                        resultSet.getString("productDescription"),
                        resultSet.getDouble("productLength"),
                        resultSet.getDouble("productWidth"),
                        resultSet.getDouble("productWeight"),
                        resultSet.getString("productCreationTimestampUTC"));

                System.out.println(productDetails);
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());

        }
    }


}
