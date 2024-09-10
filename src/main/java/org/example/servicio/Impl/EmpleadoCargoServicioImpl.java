package org.example.servicio.Impl;

import org.example.DAO.CargoDAO;
import org.example.DAO.EmpleadoDAO;
import org.example.modelo.Cargo;
import org.example.modelo.Empleado;
import org.example.servicio.Interfaz.EmpleadoCargoServicio;

public class EmpleadoCargoServicioImpl implements EmpleadoCargoServicio {
    private final EmpleadoDAO empleadoDAO;
    private final CargoDAO cargoDAO;
    public EmpleadoCargoServicioImpl(EmpleadoDAO empleadoDAO, CargoDAO cargoDAO) {
        this.empleadoDAO = empleadoDAO;
        this.cargoDAO = cargoDAO;
    }
    @Override
    public void asignarCargoAEmpleado(int empleadoId, int cargoId) {
        Empleado empleado = empleadoDAO.buscarPorId(empleadoId);
        Cargo cargo = cargoDAO.buscarPorId(cargoId);
        if (empleado != null && cargo != null) {
            empleado.setCargo(cargo);
            empleadoDAO.actualizar(empleado, empleadoId);
            System.out.println("Cargo asignado correctamente al empleado.");
        } else {
            System.out.println("Empleado o Cargo no encontrado.");
        }
    }
}
