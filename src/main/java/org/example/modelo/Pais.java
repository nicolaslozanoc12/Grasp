package org.example.modelo;

public class Pais {
    private int id;
    private String nombre;

    public Pais() {
    }
    public Pais(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString() {
        return nombre ;
    }
}
