package org.example.modelo;

public class Municipio {
    private int id;
    private String nombre;
    private Departamento departamento;
    public Municipio( String nombre, int id_departamento ) {
        this.nombre = nombre;
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
