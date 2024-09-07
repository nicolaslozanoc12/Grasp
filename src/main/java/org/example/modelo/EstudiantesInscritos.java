package org.example.modelo;

import java.util.List;

public class EstudiantesInscritos {
    private int id;
    private List<Estudiante> estudiantesInscritos;

    public EstudiantesInscritos(int id, List<Estudiante> estudiantesInscritos) {
        this.id = id;
        this.estudiantesInscritos = estudiantesInscritos;
    }

    public EstudiantesInscritos() {
    }

}
