package org.example.controlador;

import org.example.ImplDAO.PersonalInscritoDAOImpl;
import org.example.modelo.PersonalInscrito;
import org.example.servicio.Impl.PersonalInscritoServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class PersonalInscritoControlador {
    private final Servicio<PersonalInscrito> personalInscritoServicio;

    public PersonalInscritoControlador() {
        PersonalInscritoDAOImpl personalInscritoDAO=new PersonalInscritoDAOImpl();
        this.personalInscritoServicio=new PersonalInscritoServicioImpl(personalInscritoDAO);
    }

    public void agregarPersonalInscrito(PersonalInscrito objeto) {
        personalInscritoServicio.agregar(objeto);
    }

    public void listarPersonalInscrito() {
        List<PersonalInscrito> personalInscritos = personalInscritoServicio.listarTodos();
        for (PersonalInscrito personalInscrito : personalInscritos) {
            System.out.printf("Id: %d Empleado: %s\n",
                    personalInscrito.getId(),
                    personalInscrito.getEmpleado().getNombre());
        }
    }

    public PersonalInscrito obtenerPersonalInscritoPorId(int id) {
        return personalInscritoServicio.obtenerPorId(id);
    }

    public PersonalInscrito obtenerPersonalInscritoPorNombre(String nombre) {
        return personalInscritoServicio.obtenerPorNombre(nombre);
    }

    public void actualizarPersonalInscrito(PersonalInscrito objeto, int id) {
        personalInscritoServicio.actualizar(objeto, id);
    }

    public void eliminarPersonalInscrito(int id) {
        personalInscritoServicio.eliminar(id);
    }
}
