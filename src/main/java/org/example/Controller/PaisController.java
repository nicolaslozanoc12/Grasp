package org.example.Controller;

import java.util.List;
import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudPais;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudPaisArchivo;
import org.example.modelo.Pais;

public class PaisController {
    private CrudPais crudPais;
    public PaisController(ConfigLoader configLoader) {

        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudPais = new CrudPaisArchivo();  // Implementación para archivos
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createPais(String name){
        Pais pais=new Pais(name);
        crudPais.insertar(pais);
    }
    public Pais readPais(int id){
        return crudPais.buscarPorId(id);
    }
    public void updatePais(int id,String name){
        Pais paisActualizado=new Pais(name);
        crudPais.actualizar(paisActualizado,id);
    }
    public void deletePais(int id){
        crudPais.eliminar(id);
    }
    public List<Pais> paisesList(){
        return crudPais.buscarTodos();
    }
}
