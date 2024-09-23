package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudCargo;
import org.example.CrudInterfaz.CrudEmpleado;
import org.example.CrudInterfaz.CrudPersona;
import org.example.archivos.CrudCargoArchivo;
import org.example.archivos.CrudEmpleadoArchivo;
import org.example.archivos.CrudPersonalArchivo;
import org.example.modelo.Cargo;
import org.example.modelo.Empleado;
import org.example.modelo.Persona;

public class EmpleadoController {
    private CrudEmpleado crudEmpleado;
    private CrudPersona crudPersona;
    private CrudCargo crudCargo;
    // Constructor que recibe ConfigLoader
    public EmpleadoController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudEmpleado = new CrudEmpleadoArchivo();  // Implementación para archivos
            this.crudPersona=new CrudPersonalArchivo();
            this.crudCargo=new CrudCargoArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createEmpleado(int id,String nombreCargo,Double salario){
        Persona persona=crudPersona.buscarPorId(id);
        Cargo cargo=crudCargo.buscarPorNombre(nombreCargo);
        if(persona!=null) {
            Empleado empleado = new Empleado(id,persona.getNombre(),persona.getApellidos(),persona.getDireccion(),cargo,salario);
            crudEmpleado.insertar(empleado);
        }
        System.out.println("Persona no encontrada");
    }
    public Empleado readEmpleado(int id){
        return crudEmpleado.buscarPorId(id);
    }
    public void updateEmpleado(int id,String nombreCargo,Double salario){
        Persona persona=crudPersona.buscarPorId(id);
        Cargo cargo=crudCargo.buscarPorNombre(nombreCargo);
        if(persona!=null) {
            Empleado empleado = new Empleado(id,persona.getNombre(),persona.getApellidos(),persona.getDireccion(),cargo,salario);
            crudEmpleado.actualizar(empleado,id);
        }
        System.out.println("Persona no encontrada");
    }
    public void deleteEmpleado(int id){
        crudEmpleado.eliminar(id);
    }
}
