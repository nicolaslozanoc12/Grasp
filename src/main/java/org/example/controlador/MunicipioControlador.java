package org.example.controlador;

import org.example.CrudBaseDeDatos.MunicipioBaseDeDatos;
import org.example.modelo.Municipio;
import org.example.servicio.Impl.MunicipioServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class MunicipioControlador {
    private final Servicio<Municipio> municipioServicio;

    public MunicipioControlador() {
        MunicipioBaseDeDatos municipioDAO=new MunicipioBaseDeDatos();
        this.municipioServicio=new MunicipioServicioImpl(municipioDAO);
    }

    public void agregarMunicipio(Municipio objeto) {
        municipioServicio.agregar(objeto);
    }

    public void listarMunicipio() {
        List<Municipio> municipios = municipioServicio.listarTodos();
        for (Municipio municipio : municipios) {
            System.out.printf("Id: %d Nombre: %s Departamento: %s",
                    municipio.getId(),
                    municipio.getNombre(),
                    municipio.getDepartamento().getNombre());
        }
    }

    public Municipio obtenerMunicipioPorId(int id) {
        return municipioServicio.obtenerPorId(id);
    }

    public Municipio obtenerMunicipioPorNombre(String nombre) {
        return municipioServicio.obtenerPorNombre(nombre);
    }

    public void actualizarMunicipio(Municipio objeto, int id) {
        municipioServicio.actualizar(objeto, id);
    }

    public void eliminarMunicipio(int id) {
        municipioServicio.eliminar(id);
    }
}
