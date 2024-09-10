package org.example.servicio;

import org.example.DAO.DireccionDAO;
import org.example.ImplDAO.DireccionDAOImpl;
import org.example.modelo.Direccion;

import java.util.List;

public class DireccionServicio {
    private final DireccionDAOImpl direccionDAO;

    public DireccionServicio(DireccionDAOImpl direccionDAO) {
        this.direccionDAO = direccionDAO;
    }

    public void agregarDireccion(Direccion objeto) {
        direccionDAO.insertar(objeto);
        System.out.println("Dirección insertada!");
    }

    public List<Direccion> listarDirecciones() {
        return direccionDAO.buscarTodos();
    }

    public Direccion obtenerDireccionPorId(int id) {
        return direccionDAO.buscarPorId(id);
    }

    public Direccion obtenerDireccionPorNombre(String nombre) {
        return direccionDAO.buscarPorNombre(nombre);
    }

    public void actualizarDireccion(Direccion objeto, int id) {
        direccionDAO.actualizar(objeto, id);
        System.out.println("Dirección actualizada con éxito");
    }

    public void eliminarDireccion(int id) {
        direccionDAO.eliminar(id);
        System.out.println("Dirección eliminada");
    }
}
