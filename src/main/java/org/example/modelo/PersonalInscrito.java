package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class PersonalInscrito {
    private int id;
    private Empleado Empleado;

    public PersonalInscrito(int id, org.example.modelo.Empleado empleado) {
        this.id = id;
        Empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public org.example.modelo.Empleado getEmpleado() {
        return Empleado;
    }

    public PersonalInscrito() {

    }
}
