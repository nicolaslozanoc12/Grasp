package org.example.servicio.Impl;

import org.example.DAO.EmpleadoDAO;
import org.example.DAO.EstudianteDAO;
import org.example.modelo.Estudiante;
import org.example.servicio.Interfaz.EstudianteServicio;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class EstudianteServicioImpl implements EstudianteServicio {
    private final EstudianteDAO estudianteDAO;

    public EstudianteServicioImpl(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public void agregar(Estudiante objeto) {
        estudianteDAO.insertar(objeto);
        System.out.println("Estudiante insertado!");
    }

    @Override
    public List<Estudiante> listarTodos() {
        return estudianteDAO.buscarTodos();
    }

    @Override
    public Estudiante obtenerPorId(int id) {
        return estudianteDAO.buscarPorId(id);
    }

    @Override
    public Estudiante obtenerPorNombre(String nombre) {
        return estudianteDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Estudiante objeto, int id) {
        estudianteDAO.actualizar(objeto, id);
        System.out.println("Estudiante actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        estudianteDAO.eliminar(id);
        System.out.println("Estudiante eliminado");
    }
}
