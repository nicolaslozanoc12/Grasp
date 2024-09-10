package org.example.servicio.Impl;

import org.example.DAO.PersonalDAO;
import org.example.modelo.Persona;
import org.example.servicio.Interfaz.PersonalServicio;

import java.util.List;

public class PersonalServicioImpl implements PersonalServicio {
    private final PersonalDAO personalDAO;

    public PersonalServicioImpl(PersonalDAO personalDAO) {
        this.personalDAO = personalDAO;
    }

    @Override
    public void agregar(Persona objeto) {
        personalDAO.insertar(objeto);
        System.out.println("Personal insertado!");
    }

    @Override
    public List<Persona> listarTodos() {
        return personalDAO.buscarTodos();
    }

    @Override
    public Persona obtenerPorId(int id) {
        return personalDAO.buscarPorId(id);
    }

    @Override
    public Persona obtenerPorNombre(String nombre) {
        return personalDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Persona objeto, int id) {
        personalDAO.actualizar(objeto, id);
        System.out.println("Personal actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        personalDAO.eliminar(id);
        System.out.println("Personal eliminado");
    }
}
