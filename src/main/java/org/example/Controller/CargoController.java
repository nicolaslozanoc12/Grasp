package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudBaseDeDatos.CrudCargoBaseDeDatos;
import org.example.CrudInterfaz.CrudCargo;
import org.example.archivos.CrudCargoArchivo;
import org.example.modelo.Cargo;

public class CargoController {
    private CrudCargo crudCargo;

    // Constructor que recibe ConfigLoader
    public CargoController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            this.crudCargo = new CrudCargoBaseDeDatos(configLoader);  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudCargo = new CrudCargoArchivo();  // Implementación para archivos
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createCargo(String name) {
        Cargo cargo = new Cargo(name);
        crudCargo.insertar(cargo);
    }
    public Cargo readCargo(int id){
        return crudCargo.buscarPorId(id);
    }
    public void updateCargo(int id,String name){
        Cargo cargoActualizado =new Cargo(name);
        crudCargo.actualizar(cargoActualizado,id);
    }
    public void deleteCargo(int id){
        crudCargo.eliminar(id);
    }

}
