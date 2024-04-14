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
                            "INSERT INTO vehiculo "
                                    + "VALUES (?,?,?,?,?,?)");
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
                                    + "SET id_prop = ?"
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
                            "DELETE FROM vehiculo WHERE mat_veh = ? ");

            s.setString(1, mat_veh);


            int del = s.executeUpdate();
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

    public static ArrayList<String> recoverAll(ConnectionDB connect) {

        try {
            ArrayList<String> datos = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, v.desc_veh, p.id_prop, p.nombre_prop, p.dni_prop "
                                    + "FROM vehiculo v, propietario p "
                                    + "WHERE v.id_prop = p.id_prop "
                    );

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                datos.add("Matricula: " + rs.getString("mat_veh") + ", marca: " + rs.getString("marca_veh") + ", km: " + rs.getInt("kms_veh") + ", precio " + rs.getInt("precio_veh") + ", Descripcion: " + rs.getString("desc_veh") + ", id propietario: " + rs.getInt("id_prop") + ", nombre propietario: " + rs.getString("nombre_prop") + ", dni propietario: " + rs.getString("dni_prop"));
            }

            connect.closeConnection();
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static ArrayList<String> recoverAllBrand(ConnectionDB connect, String marca_veh) {

        try {
            ArrayList<String> data = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, v.desc_veh, p.id_prop, p.nombre_prop, p.dni_prop "
                                    + "FROM vehiculo v, propietario p "
                                    + "WHERE v.id_prop = p.id_prop "
                                    + "AND v.marca_veh = ?"
                    );

            s.setString(1, marca_veh);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                data.add("Matricula: " + rs.getString("mat_veh") + ", marca: " + rs.getString("marca_veh") + ", km: " + rs.getInt("kms_veh") + ", precio " + rs.getInt("precio_veh") + ", Descripcion: " + rs.getString("desc_veh") + ", id propietario: " + rs.getInt("id_prop") + ", nombre propietario: " + rs.getString("nombre_prop") + ", dni propietario: " + rs.getString("dni_prop"));
            }

            connect.closeConnection();
            return data;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static ArrayList<String> recoverVeh(ConnectionDB connect) {

        try {
            ArrayList<String> data = new ArrayList<>();

            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement(
                            "SELECT v.mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, v.desc_veh, v.id_prop "
                                    + "FROM vehiculo v "
                    );


            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                data.add("Matricula: " + rs.getString("mat_veh") + ", marca: " + rs.getString("marca_veh") + ", km: " + rs.getInt("kms_veh") + ", precio " + rs.getInt("precio_veh") + ", Descripcion: " + rs.getString("desc_veh") + ", id propietario: " + rs.getInt("id_prop"));
            }

            connect.closeConnection();
            return data;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}



