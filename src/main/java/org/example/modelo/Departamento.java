package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Departamento{
    private int id;
    private String nombre;
    private Pais pais;

    public Departamento(int id, String nombre, Pais pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais=pais;
    }

    public Departamento() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Pais getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais=" + pais +
                '}';
    }

}

