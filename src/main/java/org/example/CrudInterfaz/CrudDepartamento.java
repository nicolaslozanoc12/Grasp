package org.example.CrudInterfaz;

import org.example.modelo.Departamento;

public interface CrudDepartamento extends Crud<Departamento> {
    Departamento buscarPorNombre(String nombre);

}
