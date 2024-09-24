package org.example.CrudInterfaz;



import org.example.Config.ConfigLoader;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public interface Crud<T>{
    final Logger logger = Logger.getLogger(Crud.class.getName());
    void insertar(T objeto);
    List<T> buscarTodos();
    T buscarPorId(int id);
    void actualizar(T objeto, int id);
    void eliminar(int id);

}

