package org.example.CrudInterfaz;

import org.example.modelo.Municipio;

public interface CrudMunicipio extends Crud<Municipio> {
    Municipio buscarPorNombre(String nombre);
}
