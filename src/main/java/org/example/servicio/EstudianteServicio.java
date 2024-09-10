package org.example.servicio;

import org.example.ImplDAO.EstudianteDAOImpl;
import org.example.modelo.Estudiante;

import java.util.List;

public class EstudianteServicio {
    private final EstudianteDAOImpl estudianteDAO=new EstudianteDAOImpl();

    public EstudianteServicio() {
    }

    public void agregarEstudiante(Estudiante objeto) {
        estudianteDAO.insertar(objeto);
        System.out.println("Estudiante insertado!");
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteDAO.buscarTodos();
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        return estudianteDAO.buscarPorId(id);
    }

    public Estudiante obtenerEstudiantePorNombre(String nombre) {
        return estudianteDAO.buscarPorNombre(nombre);
    }

    public void actualizarEstudiante(Estudiante objeto, int id) {
        estudianteDAO.actualizar(objeto, id);
        System.out.println("Estudiante actualizado con éxito");
    }

    public void eliminarEstudiante(int id) {
        estudianteDAO.eliminar(id);
        System.out.println("Estudiante eliminado");
    }
}
