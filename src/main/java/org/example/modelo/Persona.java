package org.example.modelo;

public class Persona {
    private int id;
    private final String nombre;
    private final String apellidos;
    private final Direccion direccion;

    public Persona( String nombre, String apellidos, Direccion direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String informacion() {
        return ", Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion=" + direccion.toString() +
                '}';
    }
    @Override
    public String toString(){
        return informacion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
}
