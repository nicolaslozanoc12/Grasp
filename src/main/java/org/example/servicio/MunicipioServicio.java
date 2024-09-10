package org.example.servicio;

import org.example.ImplDAO.MunicipioDAOImpl;
import org.example.modelo.Municipio;

import java.util.List;

public class MunicipioServicio {
    private final MunicipioDAOImpl municipioDAO;

    public MunicipioServicio(MunicipioDAOImpl municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    public void agregarMunicipio(Municipio objeto) {
        municipioDAO.insertar(objeto);
        System.out.println("Municipio insertado!");
    }

    public List<Municipio> listarMunicipios() {
        return municipioDAO.buscarTodos();
    }

    public Municipio obtenerMunicipioPorId(int id) {
        return municipioDAO.buscarPorId(id);
    }

    public Municipio obtenerMunicipioPorNombre(String nombre) {
        return municipioDAO.buscarPorNombre(nombre);
    }

    public void actualizarMunicipio(Municipio objeto, int id) {
        municipioDAO.actualizar(objeto, id);
        System.out.println("Municipio actualizado con éxito");
    }

    public void eliminarMunicipio(int id) {
        municipioDAO.eliminar(id);
        System.out.println("Municipio eliminado");
    }
}
