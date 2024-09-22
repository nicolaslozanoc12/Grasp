package org.example.controlador;

import org.example.CrudBaseDeDatos.EmpleadoBaseDeDatos;
import org.example.modelo.Empleado;
import org.example.servicio.Impl.EmpleadoServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class EmpleadoControlador {
    private final Servicio<Empleado> empleadoServicio;

    public EmpleadoControlador() {
        EmpleadoBaseDeDatos empleadoDAO=new EmpleadoBaseDeDatos();
        this.empleadoServicio=new EmpleadoServicioImpl(empleadoDAO);
    }

    public void agregarEmpleado(Empleado objeto) {
        empleadoServicio.agregar(objeto);
    }

    public void listarEmpleado() {
        List<Empleado> empleados = empleadoServicio.listarTodos();
        for (Empleado empleado : empleados) {
            System.out.println("Id: " + empleado.getId() + " Cargo: " + empleado.getCargo() + " Salario: " + empleado.getSalario());
        }
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        return empleadoServicio.obtenerPorId(id);
    }

    public Empleado obtenerEmpleadoPorNombre(String nombre) {
        return empleadoServicio.obtenerPorNombre(nombre);
    }

    public void actualizarEmpleado(Empleado objeto, int id) {
        empleadoServicio.actualizar(objeto, id);
    }

    public void eliminarEmpleado(int id) {
        empleadoServicio.eliminar(id);
    }

}
