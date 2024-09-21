package org.example.modelo;

public class Empleado extends Persona {
    private Cargo cargo;
    private Double salario;


    public Empleado(int id, String nombre, String apellidos, Direccion direccion, Cargo cargo, Double salario) {
        super(id, nombre, apellidos, direccion);
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public String toString(){
        return super.toString()+ cargo + "salario:" + salario;
    }


    public Cargo getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
