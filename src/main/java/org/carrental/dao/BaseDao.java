package org.carrental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
    final String Url = "jdbc:mysql://localhost:3306/carrental";
    final String Username = "root";
    final String Password = "root";
    Connection conn;

    public BaseDao() {

        try {
            conn = DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
