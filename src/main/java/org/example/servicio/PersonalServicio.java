package org.example.servicio;

import org.example.ImplDAO.PersonalDAOImpl;
import org.example.modelo.Persona;

import java.util.List;

public class PersonalServicio {
    private final PersonalDAOImpl personalDAO;

    public PersonalServicio(PersonalDAOImpl personalDAO) {
        this.personalDAO = personalDAO;
    }

    public void agregarPersonal(Persona objeto) {
        personalDAO.insertar(objeto);
        System.out.println("Personal insertado!");
    }

    public List<Persona> listarPersonal() {
        return personalDAO.buscarTodos();
    }

    public Persona obtenerPersonalPorId(int id) {
        return personalDAO.buscarPorId(id);
    }

    public Persona obtenerPersonalPorNombre(String nombre) {
        return personalDAO.buscarPorNombre(nombre);
    }

    public void actualizarPersonal(Persona objeto, int id) {
        personalDAO.actualizar(objeto, id);
        System.out.println("Personal actualizado con éxito");
    }

    public void eliminarPersonal(int id) {
        personalDAO.eliminar(id);
        System.out.println("Personal eliminado");
    }
}
