package org.example.CrudInterfaz;

import org.example.modelo.Cargo;

public interface CrudCargo extends Crud<Cargo> {
    Cargo buscarPorNombre(String nombre);
}
