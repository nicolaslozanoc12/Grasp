package org.example.controlador;

import org.example.ImplDAO.DepartamentoDAOImpl;
import org.example.modelo.Departamento;
import org.example.servicio.DepartamentoServicio;

import java.util.List;

public class DepartamentoControlador {
    private final DepartamentoServicio departamentoServicio=new DepartamentoServicio();

    public DepartamentoControlador() {

    }

    public void agregarDepartamento(Departamento objeto) {
        departamentoServicio.agregarDepartamento(objeto);
    }

    public void listarDepartamentos() {
        List<Departamento> departamentos = departamentoServicio.listarDepartamentos();
        for (Departamento departamento : departamentos) {
            System.out.println("Id: " + departamento.getId() + " Nombre: " + departamento.getNombre() + " Pais: " + departamento.getPais());
        }
    }

    public Departamento obtenerDepartamentoPorId(int id) {
        return departamentoServicio.obtenerDepartamentoPorId(id);
    }

    public Departamento obtenerDepartamentoPorNombre(String nombre) {
        return departamentoServicio.obtenerDepartamentoPorNombre(nombre);
    }

    public void actualizarDepartamento(Departamento objeto, int id) {
        departamentoServicio.actualizarDepartamento(objeto, id);
    }

    public void eliminarDepartamento(int id) {
        departamentoServicio.eliminarDepartamento(id);
    }
}
