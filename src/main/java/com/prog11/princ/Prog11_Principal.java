package com.prog11.princ;

import com.prog11.bbdd.ConnectionDB;
import com.prog11.bbdd.PropietariosDAO;
import com.prog11.bbdd.VehiculosDAO;

import java.sql.SQLException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Prog11_Principal {

    public static void main(String[] args) throws SQLException {

        ArrayList<String> data;
        ConnectionDB connect = new ConnectionDB();

        System.out.println("\tEjercicio 1 ");
        System.out.println("{Insertar varios vehículos y propietarios}");

        if (PropietariosDAO.insProp(connect, 6, "Propietario 1", "88888888C") == 0) {
            System.out.println("Se ha insertado el propietario número 1");
        } else {
            System.out.println("Ha habido un error");
        }

        if (PropietariosDAO.insProp(connect, 7, "Propietario 2", "99999999V") == 0) {
            System.out.println("Se ha insertado el propietario número 2");
        } else {
            System.out.println("Ha habido un error");
        }

        if (VehiculosDAO.insVeh(connect, "4444AAA", "Seat", 11111, 1111, "Ibiza", 7) == 0) {
            System.out.println("Se ha insertado el vehículo número 1");
        } else {
            System.out.println("Ha habido un error");
        }

        if (VehiculosDAO.insVeh(connect, "5555BBB", "Honda", 222222, 22222, "Honda", 5) == 0) {
            System.out.println("Se ha insertado el vehículo número 2}");
        } else {
            System.out.println("Ha habido un error");
        }

        System.out.println("");
        System.out.println("\tEjercicio 2");
        System.out.println("{Listar todos los vehículos}");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;

        for (String dato : data) {
            System.out.println("{" + dato + "}");
        }
        System.out.println("Actualizar propietario de un vehículo.");

        if (VehiculosDAO.updateVeh(connect, "2230FVD", 7) == 0) {
            System.out.println("Vehiculo con matricula 2230FVD ha cambiado de propietario con id 7");
        } else {
            System.out.println("No se ha podido cambiar el propietario");
        }

        System.out.println("Listar todos los vehículos.");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String dato : data) {
            System.out.println(dato);
        }



    }

}