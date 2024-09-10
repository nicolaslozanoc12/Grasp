package org.example.controlador;

import org.example.ImplDAO.PersonalInscritoDAOImpl;
import org.example.modelo.PersonalInscrito;
import org.example.servicio.PersonalInscritoServicio;

import java.util.List;

public class PersonalInscritoControlador {
    private final PersonalInscritoServicio personalInscritoServicio;

    public PersonalInscritoControlador(PersonalInscritoServicio personalInscritoServicio) {
        this.personalInscritoServicio = personalInscritoServicio;
    }

    public void agregarPersonalInscrito(PersonalInscrito objeto) {
        personalInscritoServicio.agregarPersonalInscrito(objeto);
    }

    public void listarPersonalInscrito() {
        List<PersonalInscrito> personalInscritos = personalInscritoServicio.listarPersonalInscrito();
        for (PersonalInscrito personalInscrito : personalInscritos) {
            System.out.printf("Id: %d Empleado: %s\n",
                    personalInscrito.getId(),
                    personalInscrito.getEmpleado().getNombre());
        }
    }

    public PersonalInscrito obtenerPersonalInscritoPorId(int id) {
        return personalInscritoServicio.obtenerPersonalInscritoPorId(id);
    }

    public PersonalInscrito obtenerPersonalInscritoPorNombre(String nombre) {
        return personalInscritoServicio.obtenerPersonalInscritoPorNombre(nombre);
    }

    public void actualizarPersonalInscrito(PersonalInscrito objeto, int id) {
        personalInscritoServicio.actualizarPersonalInscrito(objeto, id);
    }

    public void eliminarPersonalInscrito(int id) {
        personalInscritoServicio.eliminarPersonalInscrito(id);
    }
}
