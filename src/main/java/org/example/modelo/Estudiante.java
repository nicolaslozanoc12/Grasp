package org.example.modelo;

public class Estudiante extends Persona{
    private String codigo;
    private String programa;
    private  Double promedio;


    public Estudiante(String nombre, String apellidos, Direccion direccion, String codigo, String programa, Double promedio) {
        super(nombre, apellidos, direccion);
        this.codigo = codigo;
        this.programa = programa;
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigo='" + codigo + '\'' +
                ", programa='" + programa + '\'' +
                ", promedio=" + promedio +
                super.toString()+
                "\n ";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPrograma() {
        return programa;
    }

    public Double getPromedio() {
        return promedio;
    }
}
