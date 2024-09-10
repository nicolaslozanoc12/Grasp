package org.example.servicio.Impl;

import org.example.DAO.PersonalInscritoDAO;
import org.example.modelo.PersonalInscrito;
import org.example.servicio.Interfaz.PersonalInscritoServicio;

import java.util.List;

public class PersonalInscritoServicioImpl implements PersonalInscritoServicio {
    private final PersonalInscritoDAO personalInscritoDAO;

    public PersonalInscritoServicioImpl(PersonalInscritoDAO personalInscritoDAO) {
        this.personalInscritoDAO = personalInscritoDAO;
    }

    @Override
    public void agregar(PersonalInscrito objeto) {
        personalInscritoDAO.insertar(objeto);
        System.out.println("Personal inscrito agregado!");
    }

    @Override
    public List<PersonalInscrito> listarTodos() {
        return personalInscritoDAO.buscarTodos();
    }

    @Override
    public PersonalInscrito obtenerPorId(int id) {
        return personalInscritoDAO.buscarPorId(id);
    }

    @Override
    public PersonalInscrito obtenerPorNombre(String nombre) {
        return personalInscritoDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(PersonalInscrito objeto, int id) {
        personalInscritoDAO.actualizar(objeto, id);
        System.out.println("Personal inscrito actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        personalInscritoDAO.eliminar(id);
        System.out.println("Personal inscrito eliminado");
    }
}
