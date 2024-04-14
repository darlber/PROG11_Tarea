package com.prog11.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropietariosDAO {

    public static int insProp(ConnectionDB connect, int id_prop, String nombre_prop, String dni_prop) throws SQLException {
        try {
            //abrimos conexion
            connect.openConnection();

            //preparedStatement para consultas preparadas con parametros
            PreparedStatement s = connect.getConnection()
                    .prepareStatement("INSERT INTO propietario(id_prop, nombre_prop, dni_prop) "
                            + "VALUES (?,?,?)");

            //a las variables ? les otorgaremos los valores que les pasemos
            s.setInt(1, id_prop);
            s.setString(2, nombre_prop);
            s.setString(3, dni_prop);
            s.execute();

            //cerramos consulta para liberar recursos
            s.close();

            //cerramos conexion para liberar recursos
            connect.closeConnection();

            //si va bien, devuelve 0
            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static ArrayList<String> getVeh(ConnectionDB connect, String dni_prop) {

        try {
            ArrayList<String> data = new ArrayList<>();

            connect.openConnection();
            PreparedStatement s = connect.getConnection()
                    .prepareStatement("SELECT * "
                            + "FROM vehiculo v, propietario p "
                            + "WHERE v.id_prop = p.id_prop "
                            + "AND p.dni_prop = ?");

            s.setString(1, dni_prop);

            ResultSet rs = s.executeQuery();

            //bucle while, next devolvera true si el puntero consigue avanzar al siguiente registro
            while (rs.next()) {
                data.add("ID-Propietario:" + rs.getInt("id_prop")
                        + " |DNI-Propietario: " + rs.getString("dni_prop")
                        + " |Nombre-Propietario: " + rs.getString("nombre_prop")
                        + " |Matrícula: " + rs.getString("mat_veh")
                        + " |Marca: " + rs.getString("marca_veh")
                        + " |KM: " + rs.getInt("kms_veh")
                        + " |Precio " + rs.getInt("precio_veh")
                        + " |Descripción: " + rs.getString("desc_veh")

                );
            }

            connect.closeConnection();
            return data;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //devuelve null si hubo problemas
        return null;
    }

    public static int delProp(ConnectionDB connect, String dni_prop) {

        try {
            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement("DELETE FROM propietario " +
                            "WHERE dni_prop = ?");

            s.setString(1, dni_prop);

            int del = s.executeUpdate();

            s.close();
            connect.closeConnection();

            //devuelve el numero int de los registros eliminados
            return del;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}