package org.example.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseManager {
    private Properties properties;
    private Connection connection;

    public DatabaseManager(Properties properties) {
        this.properties = properties;
    }

    // Método para conectar a la base de datos
    public void connect() {
        try {
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            System.out.println("Username: "+username+"Password: "+password);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void checkAndCreateTables() {
        List<String> tablesToCheck = new ArrayList<>();
        tablesToCheck.add("paises");
        tablesToCheck.add("departamentos");
        tablesToCheck.add("municipios");
        tablesToCheck.add("direcciones");
        tablesToCheck.add("personales");
        tablesToCheck.add("cargos");
        tablesToCheck.add("empleados");
        tablesToCheck.add("estudiantes");
        tablesToCheck.add("estudiantesinscrito");
        tablesToCheck.add("PersonalInscrito");
        tablesToCheck.add("listadoTodos");

        for (String tableName : tablesToCheck) {
            if (!doesTableExist(tableName)) {
                createTable(tableName);
            }
        }
    }

    // Método para verificar si una tabla ya existe
    private boolean doesTableExist(String tableName) {
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                System.out.println("Tabla " + tableName + " ya existe.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para crear la tabla si no existe
    private void createTable(String tableName) {
        String createSQL = "";

        switch (tableName) {
            case "paises":
                createSQL = "CREATE TABLE IF NOT EXISTS paises(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL)";
                break;

            case "departamentos":
                createSQL = "CREATE TABLE IF NOT EXISTS departamentos(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL, id_pais INT, FOREIGN KEY(id_pais) REFERENCES paises(id))";
                break;

            case "municipios":
                createSQL = "CREATE TABLE IF NOT EXISTS municipios(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL, id_departamento INT, FOREIGN KEY(id_departamento) REFERENCES departamentos(id))";
                break;

            case "direcciones":
                createSQL = "CREATE TABLE IF NOT EXISTS direcciones(id INT AUTO_INCREMENT PRIMARY KEY, id_pais INT, id_departamento INT, id_municipio INT, calle VARCHAR(100), carrera VARCHAR(100), coordenada VARCHAR(200), descripcion VARCHAR(200), " +
                        "FOREIGN KEY(id_pais) REFERENCES paises(id), FOREIGN KEY(id_departamento) REFERENCES departamentos(id), FOREIGN KEY(id_municipio) REFERENCES municipios(id))";
                break;

            // Agregar otros casos de tablas aquí...

            default:
                System.out.println("Tabla no reconocida: " + tableName);
                return;
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createSQL);
            System.out.println("Tabla " + tableName + " creada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Método para cerrar la conexión
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
