package org.example.servicio;

import org.example.ImplDAO.PaisDAOImpl;
import org.example.modelo.Pais;

import java.util.List;

public class PaisServicio {
    private final PaisDAOImpl paisDAO=new PaisDAOImpl();

    public PaisServicio() {
    }

    public void agregarPais(Pais objeto) {
        paisDAO.insertar(objeto);
        System.out.println("Pais insertado!");
    }

    public List<Pais> listarPaises() {
        return paisDAO.buscarTodos();
    }

    public Pais obtenerPaisPorId(int id) {
        return paisDAO.buscarPorId(id);
    }

    public Pais obtenerPaisPorNombre(String nombre) {
        return paisDAO.buscarPorNombre(nombre);
    }

    public void actualizarPais(Pais objeto, int id) {
        paisDAO.actualizar(objeto, id);
        System.out.println("Pais actualizado con éxito");
    }

    public void eliminarPais(int id) {
        paisDAO.eliminar(id);
        System.out.println("Pais eliminado");
    }
}
