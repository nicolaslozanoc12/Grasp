package org.example.servicio;

import org.example.ImplDAO.CargoDAOImpl;
import org.example.modelo.Cargo;

import java.util.List;

public class CargoServicio {
    private final CargoDAOImpl cargoDAO;

    public CargoServicio(CargoDAOImpl cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    public void agregarCargo(Cargo objeto) {
        cargoDAO.insertar(objeto);
        System.out.println("Cargo agregado!");
    }
    public List<Cargo> listarCargos() {
        return cargoDAO.buscarTodos();
    }
    public Cargo obtenerCargoPorId(int id) {
        return cargoDAO.buscarPorId(id);
    }

    public Cargo obtenerCargoPorNombre(String nombre) {
        return cargoDAO.buscarPorNombre(nombre);
    }

    public void actualizarCargo(Cargo objeto, int id) {
        cargoDAO.actualizar(objeto, id);
        System.out.println("Cargo actualizado con éxito");
    }

    public void eliminarCargo(int id) {
        cargoDAO.eliminar(id);
        System.out.println("Cargo eliminado");
    }
}
