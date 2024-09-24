package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudEstudiante;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudDireccionArchivo;
import org.example.archivos.CrudEstudianteArchivo;
import org.example.modelo.Direccion;
import org.example.modelo.Estudiante;

public class EstudianteController {
    private CrudEstudiante crudEstudiante;
    private CrudDireccion crudDireccion;
    public EstudianteController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudEstudiante = new CrudEstudianteArchivo();
            this.crudDireccion=new CrudDireccionArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createEstudiante(String nombre, String apellido, String calleYcarrera,String codigo,String prorgama, Double promedio ){
        //Direccion direccion=crudDireccion.buscarPorCalleYCarrera(calleYcarrera);
        //Estudiante estudiante=new Estudiante(nombre,apellido,direccion,codigo,prorgama,promedio);
        //crudEstudiante.insertar(estudiante);
    }
    public Estudiante readEstuadiante(int id){
        return crudEstudiante.buscarPorId(id);
    }
    public void updateEstudiante(){
  
    }
    public void deleteEstudiante(int id){
        crudEstudiante.eliminar(id);
    }
}
