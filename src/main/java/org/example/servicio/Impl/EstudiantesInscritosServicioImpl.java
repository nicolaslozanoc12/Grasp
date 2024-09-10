package org.example.servicio.Impl;

import org.example.DAO.EstudiantesInscritosDAO;
import org.example.modelo.EstudiantesInscritos;
import org.example.servicio.Interfaz.EstudiantesInscritosServicio;

import java.util.List;

public class EstudiantesInscritosServicioImpl implements EstudiantesInscritosServicio {
    private final EstudiantesInscritosDAO estudiantesInscritosDAO;

    public EstudiantesInscritosServicioImpl(EstudiantesInscritosDAO estudiantesInscritosDAO) {
        this.estudiantesInscritosDAO = estudiantesInscritosDAO;
    }

    @Override
    public void agregar(EstudiantesInscritos objeto) {
        estudiantesInscritosDAO.insertar(objeto);
        System.out.println("Estudiantes Inscritos agregado!");
    }

    @Override
    public List<EstudiantesInscritos> listarTodos() {
        return estudiantesInscritosDAO.buscarTodos();
    }

    @Override
    public EstudiantesInscritos obtenerPorId(int id) {
        return estudiantesInscritosDAO.buscarPorId(id);
    }

    @Override
    public EstudiantesInscritos obtenerPorNombre(String nombre) {
        return estudiantesInscritosDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(EstudiantesInscritos objeto, int id) {
        estudiantesInscritosDAO.actualizar(objeto, id);
        System.out.println("Estudiantes Inscritos actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        estudiantesInscritosDAO.eliminar(id);
        System.out.println("Estudiantes Inscritos eliminado");
    }
}
