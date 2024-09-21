package org.example.CrudInterfaz;

import org.example.conexion.ConexionDatabase;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public interface Crud<T>{
    final Logger logger = Logger.getLogger(Crud.class.getName());
    ConexionDatabase conexionDatabase = new ConexionDatabase();
    Connection conexion = conexionDatabase.getConectionDataBase();
    void insertar(T objeto);
    List<T> buscarTodos();
    T buscarPorNombre(String nombre);
    T buscarPorId(int id);
    void actualizar(T objeto, int id);
    void eliminar(int id);

}
