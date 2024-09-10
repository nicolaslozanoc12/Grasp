package org.example.servicio.Impl;

import org.example.DAO.EmpleadoDAO;
import org.example.modelo.Empleado;
import org.example.servicio.Interfaz.EmpleadoServicio;

import java.util.List;

public class EmpleadoServicioImpl implements EmpleadoServicio {
    private final EmpleadoDAO empleadoDAO;

    public EmpleadoServicioImpl(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public void agregar(Empleado objeto) {
        empleadoDAO.insertar(objeto);
        System.out.println("Empleado insertado!");
    }

    @Override
    public List<Empleado> listarTodos() {
        return empleadoDAO.buscarTodos();
    }

    @Override
    public Empleado obtenerPorId(int id) {
        return empleadoDAO.buscarPorId(id);
    }

    @Override
    public Empleado obtenerPorNombre(String nombre) {
        return empleadoDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Empleado objeto, int id) {
        empleadoDAO.actualizar(objeto, id);
        System.out.println("Empleado actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        empleadoDAO.eliminar(id);
        System.out.println("Empleado eliminado");
    }
}
