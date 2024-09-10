package org.example.controlador;

import org.example.ImplDAO.EstudiantesInscritosDAOImpl;
import org.example.modelo.EstudiantesInscritos;
import org.example.servicio.EstudiantesInscritosServicio;

import java.util.List;

public class EstudiantesInscritosControlador {
    private final EstudiantesInscritosServicio estudiantesInscritosServicio;

    public EstudiantesInscritosControlador(EstudiantesInscritosServicio estudiantesInscritosServicio) {
        this.estudiantesInscritosServicio = estudiantesInscritosServicio;
    }

    public void agregarEstudiantesInscritos(EstudiantesInscritos objeto) {
        estudiantesInscritosServicio.agregarEstudiantesInscritos(objeto);
    }

    public void listarEstudiantesInscritos() {
        List<EstudiantesInscritos> estudiantesInscritos = estudiantesInscritosServicio.listarEstudiantesInscritos();
        for (EstudiantesInscritos estudiante : estudiantesInscritos) {
            System.out.printf("Id: %d Persona: %s",
                    estudiante.getId(),
                    estudiante.getEstudiante().getId());
        }
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorId(int id) {
        return estudiantesInscritosServicio.obtenerEstudiantesInscritosPorId(id);
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorNombre(String nombre) {
        return estudiantesInscritosServicio.obtenerEstudiantesInscritosPorNombre(nombre);
    }

    public void actualizarEstudiantesInscritos(EstudiantesInscritos objeto, int id) {
        estudiantesInscritosServicio.actualizarEstudiantesInscritos(objeto, id);
    }

    public void eliminarEstudiantesInscritos(int id) {
        estudiantesInscritosServicio.eliminarEstudiantesInscritos(id);
    }
}
