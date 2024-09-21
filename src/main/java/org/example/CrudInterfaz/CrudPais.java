package org.example.CrudInterfaz;

import org.example.modelo.Pais;

public interface CrudPais extends Crud<Pais> {
    Pais buscarPorNombre(String nombre);
}
