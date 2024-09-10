package org.example.controlador;

import org.example.ImplDAO.PersonalDAOImpl;
import org.example.modelo.Persona;
import org.example.servicio.PersonalServicio;

import java.util.List;

public class PersonalControlador {
    private final PersonalServicio personalServicio=new PersonalServicio();

    public PersonalControlador() {
    }

    public void agregarPersonal(Persona objeto) {
        personalServicio.agregarPersonal(objeto);
    }

    public void listarPersonal() {
        List<Persona> personal = personalServicio.listarPersonal();
        for (Persona persona : personal) {
            System.out.printf("Id: %d Nombre: %s Apellidos: %s Dirección: %s\n",
                    persona.getId(),
                    persona.getNombre(),
                    persona.getApellidos(),
                    persona.getDireccion().toString());
        }
    }

    public Persona obtenerPersonalPorId(int id) {
        return personalServicio.obtenerPersonalPorId(id);
    }

    public Persona obtenerPersonalPorNombre(String nombre) {
        return personalServicio.obtenerPersonalPorNombre(nombre);
    }

    public void actualizarPersonal(Persona objeto, int id) {
        personalServicio.actualizarPersonal(objeto, id);
    }

    public void eliminarPersonal(int id) {
        personalServicio.eliminarPersonal(id);
    }
}
