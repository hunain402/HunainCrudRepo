package org.carrental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//                                      THIS CLASS CREATES CONNECTION WITH DATA BASE


    public class BaseDao {
    final String Url = "jdbc:mysql://localhost:3306/carrental";
    final String UserName = "root";
    final String Password = "root";
    Connection conn;

    public BaseDao() {

        try {
            conn = DriverManager.getConnection(Url, UserName, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
