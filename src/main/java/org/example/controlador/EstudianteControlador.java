package org.example.controlador;

import org.example.ImplDAO.EstudianteDAOImpl;
import org.example.modelo.Estudiante;
import org.example.servicio.Impl.EstudianteServicioImpl;
import org.example.servicio.Interfaz.EstudianteServicio;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class EstudianteControlador {
    private final Servicio<Estudiante> estudianteServicio;

    public EstudianteControlador() {
        EstudianteDAOImpl estudianteDAO=new EstudianteDAOImpl();
        this.estudianteServicio=new EstudianteServicioImpl(estudianteDAO);
    }

    public void agregarEstudiante(Estudiante objeto) {
        estudianteServicio.agregar(objeto);
    }

    public void listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteServicio.listarTodos();
        for (Estudiante estudiante : estudiantes) {
            System.out.printf("Id: %d Codigo: %s Programa: %s Promedio: %.2f\n",
                    estudiante.getId(),
                    estudiante.getCodigo(),
                    estudiante.getPrograma(),
                    estudiante.getPromedio());
        }
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteServicio.obtenerPorId(id);
    }

    public Estudiante obtenerEstudiantePorNombre(String nombre) {
        return estudianteServicio.obtenerPorNombre(nombre);
    }

    public void actualizarEstudiante(Estudiante objeto, int id) {
        estudianteServicio.actualizar(objeto, id);
    }

    public void eliminarEstudiante(int id) {
        estudianteServicio.eliminar(id);
    }
}
