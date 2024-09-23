package org.example.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties=new Properties();
    private DatabaseManager dbManager;
    private String storageType;

    public String getStorageType() {
        return storageType;
    }

    public ConfigLoader(){
        loadProperties();
    }
    private void loadProperties(){
        try(InputStream input =getClass().getClassLoader().getResourceAsStream("config.properties")){
            if (input == null) {
                throw new FileNotFoundException("No se pudo encontrar el archivo config.properties en el classpath");
            }
            properties.load(input);
            // Almacenar el tipo de almacenamiento una vez cargadas las propiedades
            storageType = properties.getProperty("storage.type");
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
