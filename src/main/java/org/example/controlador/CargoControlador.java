package org.example.controlador;

import org.example.ImplDAO.CargoDAOImpl;
import org.example.modelo.Cargo;
import org.example.servicio.Impl.CargoServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class CargoControlador {
   private final Servicio<Cargo> cargoServicio;

    public CargoControlador() {
        CargoDAOImpl cargoDAO=new CargoDAOImpl();
        this.cargoServicio=new CargoServicioImpl(cargoDAO);
    }


    public void agregarCargo(Cargo objeto) {
        cargoServicio.agregar(objeto);
    }

    public void listarCargos() {
        List<Cargo> cargos = cargoServicio.listarTodos();
        for (Cargo cargo : cargos) {
            System.out.println("Id: " + cargo.getId() + " Nombre: " + cargo.getNombre());
        }
    }
    public Cargo obtenerCargoPorId(int id) {
        return cargoServicio.obtenerPorId(id);
    }

    public Cargo obtenerCargoPorNombre(String nombre) {
        return cargoServicio.obtenerPorNombre(nombre);
    }

    public void actualizarCargo(Cargo objeto, int id) {
        cargoServicio.actualizar(objeto, id);
    }

    public void eliminarCargo(int id) {
        cargoServicio.eliminar(id);
    }
}
