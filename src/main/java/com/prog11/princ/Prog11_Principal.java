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
            System.out.println("Se ha insertado el vehículo número 2");
        } else {
            System.out.println("Ha habido un error");
        }

        System.out.println("");
        System.out.println("\tEjercicio 2");
        System.out.println("{Listar todos los vehículos}");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String d : data) {
            System.out.println("{" + d + "}");
        }

        System.out.println("");
        System.out.println("\tEjercicio 3");
        System.out.println("{Actualizar propietario de un vehículo}");

        if (VehiculosDAO.updateVeh(connect, "5555BBB", 3) == 0) {
            System.out.println("Propietario con ID = 3 es ahora propietario del vehículo con matrícula 5555BBB");
        } else {
            System.out.println("No se ha podido cambiar el propietario");
        }


        System.out.println("");
        System.out.println("\tEjercicio 4");
        System.out.println("{Listar todos los vehículos}");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String d : data) {
            System.out.println("{" + d + "}");
        }
        System.out.println("");
        System.out.println("\tEjercicio 5");
        System.out.println("{Eliminar un vehículo que exista}");

        if (VehiculosDAO.delVeh(connect, "5555BBB") == 0) {
            System.out.println("El vehículo cuya matrícula es 5555BBB, ha sido eliminado correctamente");
        } else {
            System.out.println("No se ha podido eliminar el vehículo");
        }

        System.out.println("");
        System.out.println("\tEjercicio 6");
        System.out.println("{Eliminar un vehículo que no exista}");

        if (VehiculosDAO.delVeh(connect, "9999FVD") == 0) {
            System.out.println("El vehículo cuya matrícula es 9999FVD, ha sido eliminado correctamente");
        } else {
            System.out.println("No se ha podido eliminar el vehículo");
        }

        System.out.println("");
        System.out.println("\tEjercicio 7");
        System.out.println("{Listar todos los vehículos}");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String d : data) {
            System.out.println("{" + d + "}");
        }

        System.out.println("");
        System.out.println("\tEjercicio 8");
        System.out.println("{Listar todos los vehículos de una marca}");

        data = VehiculosDAO.recoverAllBrand(connect, "Citroen");
        assert data != null;
        for (String d : data) {
            System.out.println("{" + d + "}");
        }

        System.out.println("");
        System.out.println("\tEjercicio 9");
        System.out.println("{Listar todos los vehículos de un propietario}");

        data = PropietariosDAO.getVeh(connect, "12312312X");
        assert data != null;
        for (String d : data) {
            System.out.println("{" + d + "}");
        }


        System.out.println("");
        System.out.println("\tEjercicio 10");
        System.out.println("{Eliminar un propietario con vehículos}");

        if (PropietariosDAO.delProp(connect, "99999999V") != 0) {
            System.out.println("El propietario con DNI 99999999V ha sido eliminado correctamente");
        } else {
            System.out.println("No se ha podido eliminar el propietario");
        }

        System.out.println("");
        System.out.println("\tEjercicio 11");
        System.out.println("{Eliminar un propietario sin vehículos}");

        if (PropietariosDAO.delProp(connect, "88888888C") != 0) {
            System.out.println("El propietario con DNI 88888888C ha sido eliminado correctamente");
        } else {
            System.out.println("No se ha podido eliminar el propietario");
        }

    }
}