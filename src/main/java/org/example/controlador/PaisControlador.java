package org.example.controlador;

import org.example.ImplDAO.PaisDAOImpl;
import org.example.modelo.Pais;
import org.example.servicio.PaisServicio;

import java.util.List;


public class PaisControlador {
    private final PaisServicio paisServicio=new PaisServicio();

    public PaisControlador() {
    }

    public void agregarPais(Pais objeto) {
        paisServicio.agregarPais(objeto);
    }

    public void listarPaises() {
        List<Pais> paises = paisServicio.listarPaises();
        for (Pais pais : paises) {
            System.out.printf("Id: %d Nombre: %s",
                    pais.getId(),
                    pais.getNombre());
        }
    }

    public Pais obtenerPaisPorId(int id) {
        return paisServicio.obtenerPaisPorId(id);
    }

    public Pais obtenerPaisPorNombre(String nombre) {
        return paisServicio.obtenerPaisPorNombre(nombre);
    }

    public void actualizarPais(Pais objeto, int id) {
        paisServicio.actualizarPais(objeto, id);
    }

    public void eliminarPais(int id) {
        paisServicio.eliminarPais(id);
    }
}
