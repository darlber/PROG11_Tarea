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

        System.out.println("Insertar varios vehículos y propietarios.");

        if (PropietariosDAO.insProp(connect, 6, "prop 1", "12345678A") == 0) {
            System.out.println("Se ha insertado el propietario prop 1");
        } else {
            System.out.println("No se ha insertado el propietario prop 1");
        }

        if (PropietariosDAO.insProp(connect, 7, "prop 2", "12345678B") == 0) {
            System.out.println("Se ha insertado el propietario prop 2");
        } else {
            System.out.println("No se ha insertado el propietario prop 2");
        }

        if (VehiculosDAO.insVeh(connect, "123ABC", "Seat", 70000, 7000, "Ibiza", 7) == 0) {
            System.out.println("Se ha insertado el vehiculo con matricula 123ABC");
        } else {
            System.out.println("No se ha insertado el vehiculo con matricula 123ABC");
        }

        if (VehiculosDAO.insVeh(connect, "123CBA", "Peugeout", 80000, 1000, "3008", 5) == 0) {
            System.out.println("Se ha insertado el vehiculo con matricula 123CBA");
        } else {
            System.out.println("No ha insertado el vehiculo con matricula 123CBA");
        }

        System.out.println("Listar todos los vehículos.");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String dato : data) {
            System.out.println(dato);
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

        System.out.println("Eliminar un vehículo que exista.");

        if (VehiculosDAO.delVeh(connect, "2230FVD") == 0) {
            System.out.println("Se ha eliminado el vehiculo con matricula 2230FVD");
        } else {
            System.out.println("No se ha podido eliminar el vehiculo");
        }

        System.out.println("Eliminar un vehículo que no exista.");

        if (VehiculosDAO.delVeh(connect, "1111AAA") == 0) {
            System.out.println("Se ha eliminado el vehiculo con matricula 1111AAA");
        } else {
            System.out.println("No se ha podido eliminar el vehiculo");
        }

        System.out.println("Listar todos los vehículos.");

        data = VehiculosDAO.recoverAll(connect);

        assert data != null;
        for (String dato : data) {
            System.out.println(dato);
        }

        System.out.println("Listar los vehículos de una marca.");

        data = VehiculosDAO.recoverAllBrand(connect, "Citroen");

        assert data != null;
        for (String dato : data) {
            System.out.println(dato);
        }

        System.out.println("Listar todos los vehículos de un propietario.");

        data = PropietariosDAO.getVeh(connect, "03475050X");

        assert data != null;
        for (String dato : data) {
            System.out.println(dato);
        }

        System.out.println("Eliminar un propietario con vehículos.");

        if (PropietariosDAO.delProp(connect, "03475050X") == 0) {
            System.out.println("Se ha eliminado el propietario con el dni 03475050X");
        } else {
            System.out.println("No se ha eliminado el propietario con el dni 03475050X");
        }

        System.out.println("Eliminar un propietario sin vehículos.");

        if (PropietariosDAO.delProp(connect, "123456789A") == 0) {
            System.out.println("Se ha eliminado el propietario con el dni 123456789A");
        } else {
            System.out.println("No se ha eliminado el propietario con el dni 123456789A");
        }

    }

}