package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudPersona;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudDireccionArchivo;
import org.example.archivos.CrudPersonalArchivo;
import org.example.modelo.Direccion;
import org.example.modelo.Persona;

public class PersonaController {
    private CrudPersona crudPersona;
    private CrudDireccion crudDireccion;
    public PersonaController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudPersona= new CrudPersonalArchivo();
            this.crudDireccion=new CrudDireccionArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createPersona(String name, String surname, String CalleyCarrera){
        Direccion direccion=crudDireccion.buscarPorCalleYCarrera(CalleyCarrera);
        Persona persona=new Persona(name,surname,direccion);
        crudPersona.insertar(persona);

    }
    public Persona readPersona(int id){
        return crudPersona.buscarPorId(id);
    }
    public void updatePersona(int id,String name, String surname, String CalleyCarrera) {
        Direccion direccion = crudDireccion.buscarPorCalleYCarrera(CalleyCarrera);
        Persona persona = new Persona(name, surname, direccion);
        crudPersona.actualizar(persona, id);
    }
    public void deletePersona(int id){
        crudPersona.eliminar(id);
    }
}
