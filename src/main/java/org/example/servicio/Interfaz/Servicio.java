package org.example.servicio.Interfaz;

import java.util.List;

public interface Servicio<T> {
    void agregar(T objeto);
    List<T> listarTodos();
    T obtenerPorId(int id);
    T obtenerPorNombre(String nombre);
    void actualizar(T objeto, int id);
    void eliminar(int id);
}
