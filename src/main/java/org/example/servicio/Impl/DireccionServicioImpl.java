package org.example.servicio.Impl;

import org.example.DAO.DireccionDAO;
import org.example.modelo.Direccion;
import org.example.servicio.Interfaz.DireccionServicio;

import java.util.List;

public class DireccionServicioImpl implements DireccionServicio {
    private final DireccionDAO direccionDAO;

    public DireccionServicioImpl(DireccionDAO direccionDAO) {
        this.direccionDAO = direccionDAO;
    }

    @Override
    public void agregar(Direccion objeto) {
        direccionDAO.insertar(objeto);
        System.out.println("Dirección insertada!");
    }

    @Override
    public List<Direccion> listarTodos() {
        return direccionDAO.buscarTodos();
    }

    @Override
    public Direccion obtenerPorId(int id) {
        return direccionDAO.buscarPorId(id);
    }

    @Override
    public Direccion obtenerPorNombre(String nombre) {
        return direccionDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Direccion objeto, int id) {
        direccionDAO.actualizar(objeto, id);
        System.out.println("Dirección actualizada con éxito");
    }

    @Override
    public void eliminar(int id) {
        direccionDAO.eliminar(id);
        System.out.println("Dirección eliminada");
    }
}
