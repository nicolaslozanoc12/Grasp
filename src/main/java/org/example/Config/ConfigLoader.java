package org.example.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties=new Properties();
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
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driverClassName");

        // Lógica para configurar la conexión a la base de datos
        System.out.println("Configurando la base de datos con URL: " + dbUrl);
    }

    private void configureFileStorage() {
        String filePath = properties.getProperty("file.path");

        // Lógica para configurar el almacenamiento en archivos
        System.out.println("Configurando almacenamiento en archivos en la ruta: " + filePath);
    }
}
