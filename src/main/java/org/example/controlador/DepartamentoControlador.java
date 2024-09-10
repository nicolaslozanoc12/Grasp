package org.example.controlador;

import org.example.DAO.DepartamentoDAO;
import org.example.ImplDAO.DepartamentoDAOImpl;
import org.example.modelo.Departamento;
import org.example.servicio.Impl.DepartamentoServicioImpl;
import org.example.servicio.Interfaz.Servicio;

import java.util.List;

public class DepartamentoControlador {
    private final Servicio<Departamento> departamentoServicio;

    public DepartamentoControlador() {
        DepartamentoDAOImpl departamentoDAO=new DepartamentoDAOImpl();
       this.departamentoServicio=new DepartamentoServicioImpl(departamentoDAO);
    }

    public void agregarDepartamento(Departamento objeto) {
        departamentoServicio.agregar(objeto);
    }

    public void listarDepartamentos() {
        List<Departamento> departamentos = departamentoServicio.listarTodos();
        for (Departamento departamento : departamentos) {
            System.out.println("Id: " + departamento.getId() + " Nombre: " + departamento.getNombre() + " Pais: " + departamento.getPais());
        }
    }

    public Departamento obtenerDepartamentoPorId(int id) {
        return departamentoServicio.obtenerPorId(id);
    }

    public Departamento obtenerDepartamentoPorNombre(String nombre) {
        return departamentoServicio.obtenerPorNombre(nombre);
    }

    public void actualizarDepartamento(Departamento objeto, int id) {
        departamentoServicio.actualizar(objeto, id);
    }

    public void eliminarDepartamento(int id) {
        departamentoServicio.eliminar(id);
    }
}
