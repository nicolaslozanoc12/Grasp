package org.example.controlador;

import org.example.ImplDAO.DireccionDAOImpl;
import org.example.modelo.Departamento;
import org.example.modelo.Direccion;
import org.example.servicio.DireccionServicio;

import java.util.List;

public class DireccionControlador {
    private final DireccionServicio direccionServicio=new DireccionServicio();

    public DireccionControlador() {
    }

    public void agregarDireccion(Direccion objeto) {
        direccionServicio.agregarDireccion(objeto);
    }

    public void listarDirecciones() {
        List<Direccion> direcciones = direccionServicio.listarDirecciones();
        for (Direccion direccion : direcciones) {
            System.out.println("Id: " + direccion.getId() +
                    " Municipio: " + direccion.getMunicipio().getNombre() +
                    " Departamento: " + direccion.getDepartamento().getNombre() +
                    " País: " + direccion.getPais().getNombre() +
                    " Calle: " + direccion.getCalle() +
                    " Carrera: " + direccion.getCalle() +
                    " Coordenada: " + direccion.getCoordenada() +
                    " Descripción: " + direccion.getDescripcion());
        }
    }

    public Direccion obtenerDireccionPorId(int id) {
        return direccionServicio.obtenerDireccionPorId(id);
    }

    public Direccion obtenerDireccionPorNombre(String nombre) {
        return direccionServicio.obtenerDireccionPorNombre(nombre);
    }

    public void actualizarDireccion(Direccion objeto, int id) {
        direccionServicio.actualizarDireccion(objeto, id);
    }

    public void eliminarDireccion(int id) {
        direccionServicio.eliminarDireccion(id);
    }


}
