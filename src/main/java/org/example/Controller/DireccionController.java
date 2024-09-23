package org.example.Controller;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudMunicipio;
import org.example.CrudInterfaz.CrudPais;
import org.example.archivos.CrudDepartamentoArchivo;
import org.example.archivos.CrudMunicipioArchivo;
import org.example.archivos.CrudPaisArchivo;
import org.example.modelo.Departamento;
import org.example.modelo.Direccion;
import org.example.modelo.Municipio;
import org.example.modelo.Pais;

public class DireccionController {
    private CrudDireccion crudDireccion;
    private CrudMunicipio crudMunicipio;
    private CrudDepartamento crudDepartamento;
    private CrudPais crudPais;
    public DireccionController(ConfigLoader configLoader) {
        String storageType = configLoader.getStorageType();

        if ("database".equalsIgnoreCase(storageType)) {
            //this.crudCargo = new CrudCargoBaseDeDatos();  // Implementación de base de datos
        } else if ("file".equalsIgnoreCase(storageType)) {
            this.crudMunicipio = new CrudMunicipioArchivo();  // Implementación para archivos
            this.crudMunicipio=new CrudMunicipioArchivo();
            this.crudDepartamento=new CrudDepartamentoArchivo();
            this.crudPais=new CrudPaisArchivo();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento inválido: " + storageType);
        }
    }
    public void createDirección(String MunicipioName,String departamentoName,String paisName, String calle, String carrera, String coordenada, String descripcion){
        Municipio municipio=crudMunicipio.buscarPorNombre(MunicipioName);
        Departamento departamento=crudDepartamento.buscarPorNombre(departamentoName);
        Pais pais=crudPais.buscarPorNombre(paisName);
        Direccion direccion=new Direccion(municipio,departamento,pais,calle,carrera,coordenada,descripcion);
        crudDireccion.insertar(direccion);
    }
    public Direccion readDireccion(int id){
        return  crudDireccion.buscarPorId(id);
    }
    public void updateDireccion(int id,String MunicipioName,String departamentoName,String paisName, String calle, String carrera, String coordenada, String descripcion){
        Municipio municipio=crudMunicipio.buscarPorNombre(MunicipioName);
        Departamento departamento=crudDepartamento.buscarPorNombre(departamentoName);
        Pais pais=crudPais.buscarPorNombre(paisName);
        Direccion direccion=new Direccion(municipio,departamento,pais,calle,carrera,coordenada,descripcion);
        crudDireccion.actualizar(direccion,id);

    }
    public void deleteDireccion(int id){
        crudDireccion.eliminar(id);
    }
}
