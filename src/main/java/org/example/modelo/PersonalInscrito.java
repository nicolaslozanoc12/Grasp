package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class PersonalInscrito {
    private int id;
    private Persona persona;

    public PersonalInscrito(int id, Persona persona) {
        this.id = id;
        this.persona= persona;
    }

    public int getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public PersonalInscrito() {

    }
}
