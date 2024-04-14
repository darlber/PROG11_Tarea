package com.prog11.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection connect;

    //abre la conexion con nuestros datos
    void openConnection() throws SQLException {
        this.connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/concesionario",
                "root", "1234");
    }

    //cierra la conexion para liberar recursos
    public void closeConnection() throws SQLException{
        this.connect.close();
    }

    //método de acceso que proporciona la conexión a la base de datos para su uso en otras partes del código.
    public Connection getConnection(){
        return this.connect;
    }

}