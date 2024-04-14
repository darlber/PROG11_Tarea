package com.prog11.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropietariosDAO {
    public static int insProp(ConnectionDB connect, int id_prop, String nombre_prop, String dni_prop) throws SQLException {
        try {
            connect.openConnection();
            PreparedStatement s = connect.getConnection()
                    .prepareStatement("insert into propietario(id_prop, nombre_prop, dni_prop) "
                            + "Values (?,?,?)");
            connect.closeConnection();
            s.setInt(1, id_prop);
            s.setString(2, nombre_prop);
            s.setString(2, dni_prop);
            s.execute();

            s.close();
            connect.closeConnection();

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
                    .prepareStatement("select v_mat_veh, v.marca_veh, v.kms_veh, v.precio_veh, v.desc_veh," +
                            " p.ip_prop, p.nombre_prop, p.dni_prop " + "from vehiculo v, propietario p "
                            + "where v.id_prop = p.ip_prop "
                            + "and p.dni_pro p= ?");

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                data.add("Matricula: " + rs.getString("mat_veh") + ", marca: " + rs.getString("marca_veh") + ", km: " + rs.getInt("kms_veh") + ", precio " + rs.getInt("precio_veh") + ", Descripcion: " + rs.getString("desc_veh") + ", id propietario: " + rs.getInt("id_prop") + ", nombre propietario: " + rs.getString("nombre_prop") + ", dni propietario: " + rs.getString("dni_prop"));
            }

            connect.closeConnection();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int delProp(ConnectionDB connect, String dni_prop) {

        try {
            connect.openConnection();

            PreparedStatement s = connect
                    .getConnection()
                    .prepareStatement("DELETE FROM propietario WHERE dni_prop = ?");
            s.setString(1, dni_prop);

            int del = s.executeUpdate();

            s.close();
            connect.closeConnection();
            return del;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}