package org.example.servicio.Impl;

import org.example.DAO.PaisDAO;
import org.example.modelo.Pais;
import org.example.servicio.Interfaz.PaisServicio;

import java.util.List;

public class PaisServicioImpl implements PaisServicio {
    private final PaisDAO paisDAO;

    public PaisServicioImpl(PaisDAO paisDAO) {
        this.paisDAO = paisDAO;
    }

    @Override
    public void agregar(Pais objeto) {
        paisDAO.insertar(objeto);
        System.out.println("Pais insertado!");
    }

    @Override
    public List<Pais> listarTodos() {
        return paisDAO.buscarTodos();
    }

    @Override
    public Pais obtenerPorId(int id) {
        return paisDAO.buscarPorId(id);
    }

    @Override
    public Pais obtenerPorNombre(String nombre) {
        return paisDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Pais objeto, int id) {
        paisDAO.actualizar(objeto, id);
        System.out.println("Pais actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        paisDAO.eliminar(id);
        System.out.println("Pais eliminado");
    }
}
