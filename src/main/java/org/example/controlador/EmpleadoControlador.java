package org.example.controlador;

import org.example.ImplDAO.EmpleadoDAOImpl;
import org.example.modelo.Empleado;
import org.example.servicio.EmpleadoServicio;

import java.util.List;

public class EmpleadoControlador {
    private final EmpleadoServicio empleadoServicio=new EmpleadoServicio();

    public EmpleadoControlador() {
    }

    public void agregarEmpleado(Empleado objeto) {
        empleadoServicio.agregarEmpleado(objeto);
    }

    public void listarEmpleado() {
        List<Empleado> empleados = empleadoServicio.listarEmpleado();
        for (Empleado empleado : empleados) {
            System.out.println("Id: " + empleado.getId() + " Cargo: " + empleado.getCargo() + " Salario: " + empleado.getSalario());
        }
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        return empleadoServicio.obtenerEmpleadoPorId(id);
    }

    public Empleado obtenerEmpleadoPorNombre(String nombre) {
        return empleadoServicio.obtenerEmpleadoPorNombre(nombre);
    }

    public void actualizarEmpleado(Empleado objeto, int id) {
        empleadoServicio.actualizarEmpleado(objeto, id);
    }

    public void eliminarEmpleado(int id) {
        empleadoServicio.eliminarEmpleado(id);
    }

}
