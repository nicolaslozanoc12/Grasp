package org.example.modelo;

public class Municipio {
    private int id;
    private final String nombre;
    private final Departamento departamento;
    public Municipio(int id ,String nombre, Departamento departamento ) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    @Override
    public String toString() {
        return nombre;
    }
}
