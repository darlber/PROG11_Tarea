package com.prog11.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection connect;

    void openConnection() throws SQLException {
        this.connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/concesionario", "root", "1234");
    }

    public void closeConnection() throws SQLException{
        this.connect.close();
    }

    public Connection getConnection(){
        return this.connect;
    }

}