package org.example.controlador;

import org.example.ImplDAO.MunicipioDAOImpl;
import org.example.modelo.Municipio;
import org.example.servicio.MunicipioServicio;

import java.util.List;

public class MunicipioControlador {
    private final MunicipioServicio municipioServicio=new MunicipioServicio();

    public MunicipioControlador() {
    }

    public void agregarMunicipio(Municipio objeto) {
        municipioServicio.agregarMunicipio(objeto);
    }

    public void listarMunicipio() {
        List<Municipio> municipios = municipioServicio.listarMunicipios();
        for (Municipio municipio : municipios) {
            System.out.printf("Id: %d Nombre: %s Departamento: %s",
                    municipio.getId(),
                    municipio.getNombre(),
                    municipio.getDepartamento().getNombre());
        }
    }

    public Municipio obtenerMunicipioPorId(int id) {
        return municipioServicio.obtenerMunicipioPorId(id);
    }

    public Municipio obtenerMunicipioPorNombre(String nombre) {
        return municipioServicio.obtenerMunicipioPorNombre(nombre);
    }

    public void actualizarMunicipio(Municipio objeto, int id) {
        municipioServicio.actualizarMunicipio(objeto, id);
    }

    public void eliminarMunicipio(int id) {
        municipioServicio.eliminarMunicipio(id);
    }
}
