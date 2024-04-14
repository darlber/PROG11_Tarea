package com.prog11.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculosDAO {

    public static int insVeh(ConnectionDB connect, String mat_veh, String marca_veh, int kms_veh, int precio_veh, String desc_veh, int id_prop) {

        try {
            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "INSERT INTO vehiculo " +
                                    "VALUES (?,?,?,?,?,?)");
            s.setString(1, mat_veh);
            s.setString(2, marca_veh);
            s.setInt(3, kms_veh);
            s.setInt(4, precio_veh);
            s.setString(5, desc_veh);
            s.setInt(6, id_prop);

            s.executeUpdate();

            s.close();
            connect.closeConnection();
            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;

    }

    public static int updateVeh(ConnectionDB connect, String mat_veh, int id_prop) {

        try {
            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "UPDATE vehiculo "
                                    + "SET id_prop = ? "
                                    + "WHERE mat_veh = ? ");
            s.setInt(1, id_prop);
            s.setString(2, mat_veh);


            int upd = s.executeUpdate();
            if (upd == 0) {
                return -1;
            }

            s.close();
            connect.closeConnection();

            return 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static int delVeh(ConnectionDB connect, String mat_veh) {

        try {
            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "DELETE FROM vehiculo " +
                                    "WHERE mat_veh = ? ");

            s.setString(1, mat_veh);


            int del = s.executeUpdate();

            /*executeUpdate devuelve un int con las filas afectadas,
            por lo que, si no ha habido filas afectadas, devolverá el mismo valor que si ha habido un error*/
            if (del == 0) {
                return -1;
            }

            s.close();
            connect.closeConnection();
            return 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static ArrayList<String> recoverAllBrand(ConnectionDB connect, String marca_veh) {

        try {
            ArrayList<String> vehiculosMarca = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT * "
                                    + "FROM vehiculo v, propietario p "
                                    + "WHERE v.id_prop = p.id_prop "
                                    + "AND v.marca_veh = ?"
                                    + "ORDER BY v.marca_veh"
                    );

            s.setString(1, marca_veh);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                vehiculosMarca.add("Marca: " + rs.getString("marca_veh")
                        + " |Matricula: " + rs.getString("mat_veh")
                        + " |KM: " + rs.getInt("kms_veh")
                        + " |Precio " + rs.getInt("precio_veh")
                        + " |Descripción: " + rs.getString("desc_veh")
                        + " |ID-Propietario: " + rs.getInt("id_prop")
                        + " |Nombre-Propietario: " + rs.getString("nombre_prop")
                        + " |DNI-Propietario: " + rs.getString("dni_prop")
                );
            }

            connect.closeConnection();
            return vehiculosMarca;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static ArrayList<String> recoverAll(ConnectionDB connect) {

        try {
            ArrayList<String> vehiculo = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT * "
                                    + "FROM vehiculo v, propietario p "
                                    + "WHERE v.id_prop = p.id_prop "
                                    + "ORDER BY v.mat_veh"
                    );

            //executeQuery devuelve el objeto que hayamos seleccionado
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                vehiculo.add("Matrícula: " + rs.getString("mat_veh")
                        +" |Marca: " + rs.getString("marca_veh")
                        + " |KM: " + rs.getInt("kms_veh")
                        + " |Precio " + rs.getInt("precio_veh")
                        + " |Descripción: " + rs.getString("desc_veh")
                        + " |ID-Propietario: " + rs.getInt("id_prop")
                        + " |Nombre-Propietario: " + rs.getString("nombre_prop")
                        + " |DNI-Propietario: " + rs.getString("dni_prop")
                );
            }

            connect.closeConnection();
            return vehiculo;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static ArrayList<String> allVeh(ConnectionDB connect) {

        try {
            ArrayList<String> allVeh = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT * "
                                    + "FROM vehiculo"
                    );


            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                allVeh.add("Matrícula: " + rs.getString("mat_veh")
                        + " |Marca: " + rs.getString("marca_veh")
                        + " |KM: " + rs.getInt("kms_veh")
                        + " |Precio " + rs.getInt("precio_veh")
                        + " |Descripción: " + rs.getString("desc_veh")
                        + " |ID-Propietario: " + rs.getInt("id_prop")
                );
            }

            connect.closeConnection();
            return allVeh;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}



