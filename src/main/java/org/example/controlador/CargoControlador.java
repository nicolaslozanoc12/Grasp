package org.example.controlador;

import org.example.ImplDAO.CargoDAOImpl;
import org.example.modelo.Cargo;
import org.example.servicio.CargoServicio;

import java.util.List;

public class CargoControlador {
   private final CargoServicio cargoServicio;

    public CargoControlador(CargoServicio cargoServicio) { //Constructor
        this.cargoServicio = cargoServicio;
    }

    public void agregarCargo(Cargo objeto) {
        cargoServicio.agregarCargo(objeto);
    }

    public void listarCargos() {
        List<Cargo> cargos = cargoServicio.listarCargos();
        for (Cargo cargo : cargos) {
            System.out.println("Id: " + cargo.getId() + " Nombre: " + cargo.getNombre());
        }
    }
    public Cargo obtenerCargoPorId(int id) {
        return cargoServicio.obtenerCargoPorId(id);
    }

    public Cargo obtenerCargoPorNombre(String nombre) {
        return cargoServicio.obtenerCargoPorNombre(nombre);
    }

    public void actualizarCargo(Cargo objeto, int id) {
        cargoServicio.actualizarCargo(objeto, id);
    }

    public void eliminarCargo(int id) {
        cargoServicio.eliminarCargo(id);
    }
}
