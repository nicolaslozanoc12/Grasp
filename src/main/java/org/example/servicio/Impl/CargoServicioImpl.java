package org.example.servicio.Impl;

import org.example.DAO.CargoDAO;
import org.example.modelo.Cargo;
import org.example.servicio.Interfaz.CargoServicio;

import java.util.List;

public class CargoServicioImpl implements CargoServicio {
    private final CargoDAO cargoDAO;

    public CargoServicioImpl(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }
    @Override
    public void agregar(Cargo objeto) {
        cargoDAO.insertar(objeto);
        System.out.println("Cargo agregado!");
    }

    @Override
    public List<Cargo> listarTodos() {
        return cargoDAO.buscarTodos();
    }

    @Override
    public Cargo obtenerPorId(int id) {
        return cargoDAO.buscarPorId(id);
    }

    @Override
    public Cargo obtenerPorNombre(String nombre) {
        return cargoDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Cargo objeto, int id) {
        cargoDAO.actualizar(objeto, id);
        System.out.println("Cargo actualizado con éxito");

    }

    @Override
    public void eliminar(int id) {
        cargoDAO.eliminar(id);
        System.out.println("Cargo eliminado");
    }
}
