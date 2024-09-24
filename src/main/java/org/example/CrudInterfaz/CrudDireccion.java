package org.example.CrudInterfaz;

import org.example.modelo.Direccion;

public interface CrudDireccion extends Crud<Direccion>{
    public Direccion buscarPorCalleYCarrera(String calle, String carrera);
}
