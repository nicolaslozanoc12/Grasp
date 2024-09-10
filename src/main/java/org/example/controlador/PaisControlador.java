package org.example.controlador;

import org.example.ImplDAO.PaisDAOImpl;
import org.example.modelo.Pais;
import org.example.servicio.Impl.PaisServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;


public class PaisControlador {
    private final Servicio<Pais> paisServicio;

    public PaisControlador() {
        PaisDAOImpl paisDAO=new PaisDAOImpl();
        this.paisServicio=new PaisServicioImpl(paisDAO);
    }

    public void agregarPais(Pais objeto) {
        paisServicio.agregar(objeto);
    }

    public void listarPaises() {
        List<Pais> paises = paisServicio.listarTodos();
        for (Pais pais : paises) {
            System.out.printf("Id: %d Nombre: %s",
                    pais.getId(),
                    pais.getNombre());
        }
    }

    public Pais obtenerPaisPorId(int id) {
        return paisServicio.obtenerPorId(id);
    }

    public Pais obtenerPaisPorNombre(String nombre) {
        return paisServicio.obtenerPorNombre(nombre);
    }

    public void actualizarPais(Pais objeto, int id) {
        paisServicio.actualizar(objeto, id);
    }

    public void eliminarPais(int id) {
        paisServicio.eliminar(id);
    }
}
