package org.example.servicio.Impl;

import org.example.DAO.MunicipioDAO;
import org.example.modelo.Municipio;
import org.example.servicio.Interfaz.MunicipioServicio;

import java.util.List;

public class MunicipioServicioImpl implements MunicipioServicio {
    private final MunicipioDAO municipioDAO;

    public MunicipioServicioImpl(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    @Override
    public void agregar(Municipio objeto) {
        municipioDAO.insertar(objeto);
        System.out.println("Municipio insertado!");
    }

    @Override
    public List<Municipio> listarTodos() {
        return municipioDAO.buscarTodos();
    }

    @Override
    public Municipio obtenerPorId(int id) {
        return municipioDAO.buscarPorId(id);
    }

    @Override
    public Municipio obtenerPorNombre(String nombre) {
        return municipioDAO.buscarPorNombre(nombre);
    }

    @Override
    public void actualizar(Municipio objeto, int id) {
        municipioDAO.actualizar(objeto, id);
        System.out.println("Municipio actualizado con éxito");
    }

    @Override
    public void eliminar(int id) {
        municipioDAO.eliminar(id);
        System.out.println("Municipio eliminado");
    }
}
