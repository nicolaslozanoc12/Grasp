package org.example.controlador;

import org.example.ImplDAO.PaisBaseDeDatos;
import org.example.modelo.Pais;

import java.util.List;


public class PaisControlador {

    PaisBaseDeDatos paisDAO=new PaisBaseDeDatos();

    public PaisControlador() {
    }
    public void agregarPais(String nombrePais) {
        paisDAO.insertar(new Pais(nombrePais));
    }
    public void listarPaises() {
        List<Pais> paises = paisDAO.buscarTodos();
        for (Pais pais : paises) {
            System.out.printf("Id: %d Nombre: %s",
                    pais.getId(),
                    pais.getNombre());
        }
    }
    public void obtenerPaisPorId(int id) {
        Pais pais = paisDAO.buscarPorId(id);
        System.out.printf("Id: %d , nombre: %s",pais.getId(),pais.getNombre());
    }
    public void obtenerPaisPorNombre(String nombre) {
        Pais pais = paisDAO.buscarPorNombre(nombre);
        System.out.printf("Id: %d , nombre: %s",pais.getId(),pais.getNombre());
    }
    public void actualizarPais(String nombre, int id) {
        paisDAO.actualizar(new Pais(nombre), id);
    }
    public void eliminarPais(int id) {
        paisDAO.eliminar(id);
    }
}
