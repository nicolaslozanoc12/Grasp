package org.example.modelo;

import java.util.List;

public class EstudiantesInscritos {
    private int id;
    private Estudiante estudiante;

    public EstudiantesInscritos(int id, Estudiante estudiante) {
        this.id = id;
        this.estudiante = estudiante;
    }

    public EstudiantesInscritos() {
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
}
