package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudEstudiante;
import org.example.CrudInterfaz.CrudPersona;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudDireccionArchivo;
import org.example.archivos.CrudEstudianteArchivo;
import org.example.archivos.CrudPersonalArchivo;
import org.example.modelo.Direccion;
import org.example.modelo.Estudiante;
import org.example.modelo.Persona;

public class EstudianteController {
    private CrudEstudiante crudEstudiante;
    private CrudDireccion crudDireccion;
    private CrudPersona crudPersona;
    public EstudianteController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudEstudiante = new CrudEstudianteArchivo();
            this.crudDireccion=new CrudDireccionArchivo();
            this.crudPersona=new CrudPersonalArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createEstudiante(int id,String codigo,String prorgama, Double promedio ){
        Persona persona=crudPersona.buscarPorId(id);
        if(persona!=null) {
            Estudiante estudiante = new Estudiante(persona.getNombre(),persona.getApellidos(),persona.getDireccion(), codigo, prorgama, promedio);
            crudEstudiante.insertar(estudiante);
        }
    }
    public Estudiante readEstuadiante(int id){
        return crudEstudiante.buscarPorId(id);
    }
    public void updateEstudiante(int id,String codigo,String prorgama, Double promedio ){
        Persona persona=crudPersona.buscarPorId(id);
        if(persona!=null) {
            Estudiante estudiante = new Estudiante(persona.getNombre(),persona.getApellidos(),persona.getDireccion(), codigo, prorgama, promedio);
            crudEstudiante.actualizar(estudiante,id);
        }
    }
    public void deleteEstudiante(int id){
        crudEstudiante.eliminar(id);
    }
}
