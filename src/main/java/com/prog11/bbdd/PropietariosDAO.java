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
                    .prepareStatement("insert into propietario(id_prop, nombre_prop, dni_prop) "
                            + "Values (?,?,?)");

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
        ArrayList<String> data = new ArrayList<>();

        try {
            connect.openConnection();
            PreparedStatement s = connect.getConnection()
                    .prepareStatement("SELECT v_mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, v.desc_veh,"
                            + " p.ip_prop, p.nombre_prop, p.dni_prop "
                            + "FROM vehiculo v, propietario p "
                            + "WHERE v.id_prop = p.ip_prop "
                            + "AND p.dni_pro p= ?");

            ResultSet rs = s.executeQuery();

            //bucle while, next devolvera true si el puntero consigue avanzar al siguiente registro
            while (rs.next()) {
                data.add("Matricula: " + rs.getString("mat_veh")
                        + ", marca: " + rs.getString("marca_veh")
                        + ", km: " + rs.getInt("kms_veh")
                        + ", precio " + rs.getInt("precio_veh")
                        + ", Descripcion: " + rs.getString("desc_veh")
                        + ", id propietario: " + rs.getInt("id_prop")
                        + ", nombre propietario: " + rs.getString("nombre_prop")
                        + ", dni propietario: " + rs.getString("dni_prop"));
            }

            connect.closeConnection();

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