package org.example.modelo;

public class Persona implements Todos {
    private int id;
    private final String nombre;
    private final String apellidos;
    private final Direccion direccion;

    public Persona(int id, String nombre, String apellidos, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    @Override
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

    public String getApellidos() {
        return apellidos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDireccion(int id){this.id=id;}
}
