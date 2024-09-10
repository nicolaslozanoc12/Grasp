package org.example.controlador;

import org.example.ImplDAO.EstudianteDAOImpl;
import org.example.modelo.Empleado;
import org.example.modelo.Estudiante;
import org.example.servicio.EstudianteServicio;

import java.util.List;

public class EstudianteControlador {
    private final EstudianteServicio estudianteServicio=new EstudianteServicio();

    public EstudianteControlador() {
    }

    public void agregarEstudiante(Estudiante objeto) {
        estudianteServicio.agregarEstudiante(objeto);
    }

    public void listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            System.out.printf("Id: %d Codigo: %s Programa: %s Promedio: %.2f\n",
                    estudiante.getId(),
                    estudiante.getCodigo(),
                    estudiante.getPrograma(),
                    estudiante.getPromedio());
        }
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteServicio.obtenerEstudiantePorId(id);
    }

    public Estudiante obtenerEstudiantePorNombre(String nombre) {
        return estudianteServicio.obtenerEstudiantePorNombre(nombre);
    }

    public void actualizarEstudiante(Estudiante objeto, int id) {
        estudianteServicio.actualizarEstudiante(objeto, id);
    }

    public void eliminarEstudiante(int id) {
        estudianteServicio.eliminarEstudiante(id);
    }
}
