package org.example.servicio;

import org.example.ImplDAO.DepartamentoDAOImpl;
import org.example.modelo.Departamento;

import java.util.List;

public class DepartamentoServicio {
    private final DepartamentoDAOImpl departamentoDAO;

    public DepartamentoServicio(DepartamentoDAOImpl departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    public void agregarDepartamento(Departamento objeto) {
        departamentoDAO.insertar(objeto);
        System.out.println("Departamento insertado!");
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoDAO.buscarTodos();
    }

    public Departamento obtenerDepartamentoPorId(int id) {
        return departamentoDAO.buscarPorId(id);
    }

    public Departamento obtenerDepartamentoPorNombre(String nombre) {
        return departamentoDAO.buscarPorNombre(nombre);
    }

    public void actualizarDepartamento(Departamento objeto, int id) {
        departamentoDAO.actualizar(objeto, id);
        System.out.println("Departamento actualizado con éxito");
    }

    public void eliminarDepartamento(int id) {
        departamentoDAO.eliminar(id);
        System.out.println("Departamento eliminado");
    }
}
