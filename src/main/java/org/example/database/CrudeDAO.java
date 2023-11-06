package org.example.database;
import org.example.model.product.Product;
import java.util.List;
import java.sql.*;

interface productDAO{

    void getAll(Connection conn) throws SQLException;
    void saveToDB(Connection c,List<Product> products ) throws SQLException;
    //void updateToDB(T t);
    //void deleteFromDB(T t);

}
