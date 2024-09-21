package org.example.controlador;

import org.example.DAO.DepartamentoDAO;
import org.example.ImplDAO.PaisBaseDeDatos;
import org.example.modelo.Departamento;
import org.example.modelo.Pais;

import java.util.List;

public class DepartamentoControlador {
    DepartamentoDAO departamentoDAO;
    PaisBaseDeDatos paisDAO = new PaisBaseDeDatos();

    public DepartamentoControlador() {
    }

    public void agregarDepartamento(String nombreDepartamento,String nombrePais) {
        Pais pais = paisDAO.buscarPorNombre(nombrePais);
        departamentoDAO.insertar(new Departamento(0,nombreDepartamento,pais));
    }

    public void listarDepartamentos() {
        List<Departamento> departamentos = departamentoDAO.buscarTodos();
        for (Departamento departamento : departamentos) {
            System.out.println("Id: " + departamento.getId() + " Nombre: " + departamento.getNombre() + " Pais: " + departamento.getPais().getNombre());
        }
    }

    public void obtenerDepartamentoPorId(int id) {
        Departamento departamento= departamentoDAO.buscarPorId(id);
        System.out.printf("Id: %d , nombre: %s, pais : %s",departamento.getId(),departamento.getNombre(),departamento.getPais().getNombre());
    }

    public void obtenerDepartamentoPorNombre(String nombre) {
        Departamento departamento = departamentoDAO.buscarPorNombre(nombre);
        System.out.printf("Id: %d , nombre: %s , pais : %s",departamento.getId(),departamento.getNombre(),departamento.getPais().getNombre());
    }

    public void actualizarDepartamento(String nombreDepartamento,String nombrePais, int id) {
        Pais pais = paisDAO.buscarPorNombre(nombrePais);
        Departamento departamento = new Departamento(id,nombreDepartamento,pais);
        departamentoDAO.actualizar(departamento,id);
    }

    public void eliminarDepartamento(int id) {
        int id1 = id;
    }
}
