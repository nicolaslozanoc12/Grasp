package org.example.CrudInterfaz;

import org.example.modelo.Persona;

public interface CrudPersona extends Crud<Persona> {
    Persona buscarPorNombreyApellido(String nombre,String apellido);
}
