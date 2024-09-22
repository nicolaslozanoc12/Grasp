package org.example.controlador;

import org.example.CrudBaseDeDatos.EstudiantesInscritosBaseDeDatos;
import org.example.modelo.EstudiantesInscritos;
import org.example.servicio.Impl.EstudiantesInscritosServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class EstudiantesInscritosControlador {
    private final Servicio<EstudiantesInscritos> estudiantesInscritosServicio;

    public EstudiantesInscritosControlador() {
        EstudiantesInscritosBaseDeDatos estudiantesInscritosDAO=new EstudiantesInscritosBaseDeDatos();
        this.estudiantesInscritosServicio=new EstudiantesInscritosServicioImpl(estudiantesInscritosDAO);
    }

    public void agregarEstudiantesInscritos(EstudiantesInscritos objeto) {
        estudiantesInscritosServicio.agregar(objeto);
    }

    public void listarEstudiantesInscritos() {
        List<EstudiantesInscritos> estudiantesInscritos = estudiantesInscritosServicio.listarTodos();
        for (EstudiantesInscritos estudiante : estudiantesInscritos) {
            System.out.printf("Id: %d Persona: %s",
                    estudiante.getId(),
                    estudiante.getEstudiante().getId());
        }
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorId(int id) {
        return estudiantesInscritosServicio.obtenerPorId(id);
    }

    public EstudiantesInscritos obtenerEstudiantesInscritosPorNombre(String nombre) {
        return estudiantesInscritosServicio.obtenerPorNombre(nombre);
    }

    public void actualizarEstudiantesInscritos(EstudiantesInscritos objeto, int id) {
        estudiantesInscritosServicio.actualizar(objeto, id);
    }

    public void eliminarEstudiantesInscritos(int id) {
        estudiantesInscritosServicio.eliminar(id);
    }
}
