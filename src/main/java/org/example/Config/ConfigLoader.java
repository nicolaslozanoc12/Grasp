package org.example.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties=new Properties();
    private DatabaseManager dbManager;
    public ConfigLoader(){
        loadProperties();
    }
    private void loadProperties(){
        try(FileInputStream fis=new FileInputStream("config.properties")){
            properties.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void configureStorage(){
        String storageType=properties.getProperty("storage.type");
        if("database".equalsIgnoreCase(storageType)){
            configureDatabase();
        }else if("file".equalsIgnoreCase(storageType)){
            configureFileStorage();
        }
        else{
            throw new IllegalArgumentException("Invalida storage type: "+storageType);
        }
    }

    private void configureDatabase() {
        System.out.println("Configurando la base de datos...");
        dbManager = new DatabaseManager(properties);  // Pasamos las propiedades al DatabaseManager
        dbManager.connect();  // Conectamos a la base de datos
        dbManager.checkAndCreateTables();  // Verificamos y creamos tablas si es necesario

    }

    private void configureFileStorage() {
        String filePath = properties.getProperty("file.path");

        // Lógica para configurar el almacenamiento en archivos
        System.out.println("Configurando almacenamiento en archivos en la ruta: " + filePath);
    }
}
