package org.example;

import org.example.CrudInterfaz.CrudPais;
import org.example.archivos.CrudPaisImpl;
import org.example.modelo.*;

public class Main {

    //Nicolas Steven Lozano Castro
    //Nicole Michelle Bernal Galindo
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CrudPais subir = new CrudPaisImpl();
        
        subir.actualizar(new Pais(1,"Colombiana"),1);


    }
}