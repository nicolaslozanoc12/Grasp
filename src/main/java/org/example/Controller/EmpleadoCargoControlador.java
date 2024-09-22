package org.example.Controller;

import org.example.servicio.Interfaz.EmpleadoCargoServicio;

public class EmpleadoCargoControlador {
    private final EmpleadoCargoServicio empleadoCargoServicio;

    public EmpleadoCargoControlador(EmpleadoCargoServicio empleadoCargoServicio) {
        this.empleadoCargoServicio = empleadoCargoServicio;
    }
    public void asignarCargoAEmpleado(int empleadoId, int cargoId) {
        empleadoCargoServicio.asignarCargoAEmpleado(empleadoId, cargoId);
    }
}
