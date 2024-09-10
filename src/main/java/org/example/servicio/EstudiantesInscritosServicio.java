package org.example.servicio;

import org.example.ImplDAO.EstudiantesInscritosDAOImpl;
import org.example.modelo.EstudiantesInscritos;

import java.util.List;

public class EstudiantesInscritosServicio {
    private final EstudiantesInscritosDAOImpl estudiantesInscritosDAO=new EstudiantesInscritosDAOImpl();

    public EstudiantesInscritosServicio() {
    }

    public void agregarEstudiantesInscritos(EstudiantesInscritos objeto) {
        estudiantesInscritosDAO.insertar(objeto);
        System.out.println("Estudiantes Inscritos agregado!");
    }

    public List<EstudiantesInscritos> listarEstudiantesInscritos() {
        return estudiantesInscritosDAO.buscarTodos();
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorId(int id) {
        return estudiantesInscritosDAO.buscarPorId(id);
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorNombre(String nombre) {
        return estudiantesInscritosDAO.buscarPorNombre(nombre);
    }

    public void actualizarEstudiantesInscritos(EstudiantesInscritos objeto, int id) {
        estudiantesInscritosDAO.actualizar(objeto, id);
        System.out.println("Estudiantes Inscritos actualizado con éxito");
    }

    public void eliminarEstudiantesInscritos(int id) {
        estudiantesInscritosDAO.eliminar(id);
        System.out.println("Estudiantes Inscritos eliminado");
    }
}
