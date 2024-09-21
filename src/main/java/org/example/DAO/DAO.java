package org.example.DAO;

import org.example.conexion.ConexionDatabase;


import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

public interface DAO<T> {
    ConexionDatabase conexionDatabase = new ConexionDatabase();
    Connection conexion = conexionDatabase.getConectionDataBase();
    Logger logger = Logger.getLogger(ConexionDatabase.class.getName());
    void insertar(T objeto);
    T buscarPorNombre(String nombre);
    T buscarPorId(int id);
    List<T> buscarTodos();
    void actualizar(T objeto, int id);
    void eliminar(int id);
}
