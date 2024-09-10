package org.example.servicio;

import org.example.ImplDAO.PersonalInscritoDAOImpl;
import org.example.modelo.PersonalInscrito;

import java.util.List;

public class PersonalInscritoServicio {
    private final PersonalInscritoDAOImpl personalInscritoDAO=new PersonalInscritoDAOImpl();

    public PersonalInscritoServicio() {
    }

    public void agregarPersonalInscrito(PersonalInscrito objeto) {
        personalInscritoDAO.insertar(objeto);
        System.out.println("Personal inscrito agregado!");
    }

    public List<PersonalInscrito> listarPersonalInscrito() {
        return personalInscritoDAO.buscarTodos();
    }

    public PersonalInscrito obtenerPersonalInscritoPorId(int id) {
        return personalInscritoDAO.buscarPorId(id);
    }

    public PersonalInscrito obtenerPersonalInscritoPorNombre(String nombre) {
        return personalInscritoDAO.buscarPorNombre(nombre);
    }

    public void actualizarPersonalInscrito(PersonalInscrito objeto, int id) {
        personalInscritoDAO.actualizar(objeto, id);
        System.out.println("Personal inscrito actualizado con éxito");
    }

    public void eliminarPersonalInscrito(int id) {
        personalInscritoDAO.eliminar(id);
        System.out.println("Personal inscrito eliminado");
    }
}
