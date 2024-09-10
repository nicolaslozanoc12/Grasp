package org.example.servicio.Impl;

import org.example.DAO.DireccionDAO;
import org.example.DAO.PersonalDAO;
import org.example.modelo.Direccion;
import org.example.modelo.Persona;
import org.example.servicio.Interfaz.PersonaDireccionServicio;

public class PersonaDireccionServicioImpl implements PersonaDireccionServicio {
    private final PersonalDAO personalDAO;
    private final DireccionDAO direccionDAO;

    public PersonaDireccionServicioImpl(PersonalDAO personalDAO, DireccionDAO direccionDAO) {
        this.personalDAO = personalDAO;
        this.direccionDAO = direccionDAO;
    }

    @Override
    public void asignarDireccionAPersona(int personaId, int direccionId) {
        Persona persona = personalDAO.buscarPorId(personaId);
        Direccion direccion = direccionDAO.buscarPorId(direccionId);

        if (persona != null && direccion != null) {
            persona.setDireccion(direccion.getId());
            personalDAO.actualizar(persona, personaId);
            System.out.println("Dirección asignada correctamente a la persona.");
        } else {
            System.out.println("Persona o Dirección no encontrada.");
        }
    }
}
