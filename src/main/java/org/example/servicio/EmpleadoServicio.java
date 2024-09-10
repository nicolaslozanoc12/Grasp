package org.example.servicio;

import org.example.ImplDAO.EmpleadoDAOImpl;
import org.example.modelo.Empleado;

import java.util.List;

public class EmpleadoServicio {
    private final EmpleadoDAOImpl empleadoDAO=new EmpleadoDAOImpl();

    public EmpleadoServicio() {
    }

    public void agregarEmpleado(Empleado objeto) {
        empleadoDAO.insertar(objeto);
        System.out.println("Empleado insertado!");
    }

    public List<Empleado> listarEmpleado() {
        return empleadoDAO.buscarTodos();
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        return empleadoDAO.buscarPorId(id);
    }

    public Empleado obtenerEmpleadoPorNombre(String nombre) {
        return empleadoDAO.buscarPorNombre(nombre);
    }

    public void actualizarEmpleado(Empleado objeto, int id) {
        empleadoDAO.actualizar(objeto, id);
        System.out.println("Empleado actualizado con éxito");
    }

    public void eliminarEmpleado(int id) {
        empleadoDAO.eliminar(id);
        System.out.println("Empleado eliminado");
    }
}
