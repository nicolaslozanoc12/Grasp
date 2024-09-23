package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudPais;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudDepartamentoArchivo;
import org.example.archivos.CrudPaisArchivo;
import org.example.modelo.Departamento;
import org.example.modelo.Pais;

public class DepartamentoController {
    private CrudDepartamento crudDepartamento;
    private CrudPais crudPais;
    public DepartamentoController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudDepartamento = new CrudDepartamentoArchivo();// Implementación para archivos
            this.crudPais=new CrudPaisArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createDepartamento(String name, String countryName){
        Pais paisBuscado=crudPais.buscarPorNombre(countryName);
        Departamento departamento=new Departamento(name,paisBuscado);
        crudDepartamento.insertar(departamento);
    }
    public Departamento readDepartamento(int id){
        return crudDepartamento.buscarPorId(id);
    }
    public void updateDepartamento(int id,String name,String countryName){
        Pais paisBuscado=crudPais.buscarPorNombre(countryName);
        Departamento departamento=new Departamento(name,paisBuscado);
        crudDepartamento.actualizar(departamento,id);
    }
    public void deleteDepartamento(int id){
        crudDepartamento.eliminar(id);
    }
}
