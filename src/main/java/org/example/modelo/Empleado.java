package org.example.modelo;

public class Empleado extends Persona {
    private Cargo cargo;
    private Double salario;
    private Persona persona;
    private int id_persona;

    public Empleado(String nombre, String apellidos, Direccion direccion) {
        super(nombre, apellidos, direccion);
    }
    @Override
    public String toString(){
        return super.toString()+ cargo + "salario:" + salario;
    }

    public int getId_persona() {
        return id_persona;
    }

    public Cargo getCargo() {
        return cargo;
    }
    public Persona getPersona() {
        return persona;
    }
    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
