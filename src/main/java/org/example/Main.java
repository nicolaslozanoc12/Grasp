package org.example;

import org.example.Config.ConfigLoader;
import org.example.Controller.CargoController;
import org.example.CrudInterfaz.CrudPais;
import org.example.GUI.InicioFrame;
import org.example.archivos.CrudPaisArchivo;
import org.example.modelo.*;

public class Main {

    //Nicolas Steven Lozano Castro
    //Nicole Michelle Bernal Galindo
    public static void main(String[] args) {
        ConfigLoader configLoader=new ConfigLoader();
        CargoController cargoController=new CargoController(configLoader);
        InicioFrame newframe = new InicioFrame();
        newframe.setVisible(true);
    }
}