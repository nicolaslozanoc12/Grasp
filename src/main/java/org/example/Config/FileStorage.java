package org.example.Config;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileStorage {
    private Properties properties;

    public FileStorage(Properties properties) {
        this.properties = properties;
    }

    public void saveClassData(String className, String data) {
        String basePath = properties.getProperty("file.path");
        File folder = new File(basePath);

        // Verifica si el directorio existe, si no, lo crea
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Directorio creado: " + basePath);
            } else {
                System.out.println("Error al crear el directorio: " + basePath);
                return;
            }
        }

        String filePath = basePath + "/" + className + ".txt";  // Cada clase tendrá su archivo

        try {
            File file = new File(filePath);

            // Crear el archivo si no existe
            if (!file.exists()) {
                file.createNewFile();
            }

            // Escribir los datos en el archivo
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(data);
            }

            System.out.println("Datos guardados en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
