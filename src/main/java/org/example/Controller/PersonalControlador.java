package org.example.Controller;

import org.example.CrudBaseDeDatos.PersonalBaseDeDatos;
import org.example.modelo.Persona;
import org.example.servicio.Impl.PersonalServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class PersonalControlador {
    private final Servicio<Persona> personalServicio;

    public PersonalControlador() {
        PersonalBaseDeDatos personalDAO=new PersonalBaseDeDatos();
        this.personalServicio=new PersonalServicioImpl(personalDAO);
    }

    public void agregarPersonal(Persona objeto) {
        personalServicio.agregar(objeto);
    }

    public void listarPersonal() {
        List<Persona> personal = personalServicio.listarTodos();
        for (Persona persona : personal) {
            System.out.printf("Id: %d Nombre: %s Apellidos: %s Dirección: %s\n",
                    persona.getId(),
                    persona.getNombre(),
                    persona.getApellidos(),
                    persona.getDireccion().toString());
        }
    }

    public Persona obtenerPersonalPorId(int id) {
        return personalServicio.obtenerPorId(id);
    }

    public Persona obtenerPersonalPorNombre(String nombre) {
        return personalServicio.obtenerPorNombre(nombre);
    }

    public void actualizarPersonal(Persona objeto, int id) {
        personalServicio.actualizar(objeto, id);
    }

    public void eliminarPersonal(int id) {
        personalServicio.eliminar(id);
    }
}
