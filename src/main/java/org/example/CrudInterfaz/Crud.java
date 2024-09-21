package org.example.CrudInterfaz;

import java.util.List;
import java.util.logging.Logger;


public interface Crud<T>{
    final Logger LOGGER = Logger.getLogger(Crud.class.getName());
    void crear(T objeto);
    List<T> buscarTodos();
    T buscarPorNombre(String nombre);
    T buscarPorId(int id);
    void actualizar(T objeto, int id);
    void eliminar(int id);

}
