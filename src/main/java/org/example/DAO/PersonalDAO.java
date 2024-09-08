package org.example.DAO;
import org.example.modelo.Persona;

public interface PersonalDAO extends DAO<Persona> {

    Persona buscarPorNombreyApellido(String nombre, String apellido);

}
