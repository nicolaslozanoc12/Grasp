package org.example.Controller;

import org.example.CrudBaseDeDatos.DireccionBaseDeDatos;
import org.example.modelo.Direccion;
import org.example.servicio.Impl.DireccionServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class DireccionControlador {
    private final Servicio<Direccion> direccionServicio;

    public DireccionControlador() {
        DireccionBaseDeDatos direccionDAO=new DireccionBaseDeDatos();
        this.direccionServicio=new DireccionServicioImpl(direccionDAO);
    }

    public void agregarDireccion(Direccion objeto) {
        direccionServicio.agregar(objeto);
    }

    public void listarDirecciones() {
        List<Direccion> direcciones = direccionServicio.listarTodos();
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
        return direccionServicio.obtenerPorId(id);
    }

    public Direccion obtenerDireccionPorNombre(String nombre) {
        return direccionServicio.obtenerPorNombre(nombre);
    }

    public void actualizarDireccion(Direccion objeto, int id) {
        direccionServicio.actualizar(objeto, id);
    }

    public void eliminarDireccion(int id) {
        direccionServicio.eliminar(id);
    }


}
