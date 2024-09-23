package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudMunicipio;
import org.example.archivos.CrudDepartamentoArchivo;
import org.example.archivos.CrudMunicipioArchivo;
import org.example.modelo.Departamento;
import org.example.modelo.Municipio;

public class MunicipioController {
    private CrudMunicipio crudMunicipio;
    private CrudDepartamento crudDepartamento;
    public MunicipioController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudMunicipio = new CrudMunicipioArchivo();
            this.crudDepartamento=new CrudDepartamentoArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }

    public void createMunicipio(String name,String departamentoName){
        Departamento departamento=crudDepartamento.buscarPorNombre(departamentoName);
        Municipio municipio=new Municipio(name,departamento);
        crudMunicipio.insertar(municipio);
    }
    public Municipio readMunicipio(int id){
        return crudMunicipio.buscarPorId(id);
    }
    public void updateMunicipio(int id,String name,String departamentoName){
        Departamento departamento=crudDepartamento.buscarPorNombre(departamentoName);
        Municipio municipio=new Municipio(name,departamento);
        crudMunicipio.actualizar(municipio,id);
    }
    public void deleteMunicipio(int id){
    crudMunicipio.eliminar(id);
    }
}
