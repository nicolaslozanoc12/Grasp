package org.example.servicio.Impl;

import org.example.DAO.DepartamentoDAO;
import org.example.modelo.Departamento;
import org.example.servicio.Interfaz.DepartamentoServicio;

import java.util.List;

public class DepartamentoServicioImpl implements DepartamentoServicio {
    private final DepartamentoDAO departamentoDAO;

    public DepartamentoServicioImpl(DepartamentoDAO departamentoDAO) {
        this.departamentoDAO = departamentoDAO;
    }

    @Override
    public void agregar(Departamento objeto) {
        departamentoDAO.insertar(objeto);
        System.out.println("Departamento insertado!");
    }

    @Override
    public List<Departamento> listarTodos() {
            return departamentoDAO.buscarTodos();
    }

    @Override
    public Departamento obtenerPorId(int id) {
            return departamentoDAO.buscarPorId(id);
    }

    @Override
    public Departamento obtenerPorNombre(String nombre) {
        return departamentoDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Departamento objeto, int id) {
        departamentoDAO.actualizar(objeto, id);
        System.out.println("Departamento actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        departamentoDAO.eliminar(id);
        System.out.println("Departamento eliminado");
    }
}
