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
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driverClassName");

        try {
            Class.forName(dbDriver); // Cargar el driver
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Conectado a la base de datos H2 con éxito!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
                createSQL = "CREATE TABLE paises(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL)";
                break;

            case "departamentos":
                createSQL = "CREATE TABLE departamentos(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL, id_pais INT, FOREIGN KEY(id_pais) REFERENCES paises(id))";
                break;

            case "municipios":
                createSQL = "CREATE TABLE municipios(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(150) NOT NULL, id_departamento INT, FOREIGN KEY(id_departamento) REFERENCES departamentos(id))";
                break;

            case "direcciones":
                createSQL = "CREATE TABLE direcciones(id INT AUTO_INCREMENT PRIMARY KEY, id_pais INT, id_departamento INT, id_municipio INT, calle VARCHAR(100), carrera VARCHAR(100), coordenada VARCHAR(200), descripcion VARCHAR(200), " +
                        "FOREIGN KEY(id_pais) REFERENCES paises(id), FOREIGN KEY(id_departamento) REFERENCES departamentos(id), FOREIGN KEY(id_municipio) REFERENCES municipios(id))";
                break;

            case "personales":
                createSQL = "CREATE TABLE personales(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255) NOT NULL, apellidos VARCHAR(255) NOT NULL, id_direccion INT, FOREIGN KEY(id_direccion) REFERENCES direcciones(id))";
                break;

            case "cargos":
                createSQL = "CREATE TABLE cargos(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50))";
                break;

            case "empleados":
                createSQL = "CREATE TABLE empleados(id INT AUTO_INCREMENT PRIMARY KEY, id_cargo INT, salario DOUBLE, id_persona INT, " +
                        "FOREIGN KEY (id_persona) REFERENCES personales(id), FOREIGN KEY (id_cargo) REFERENCES cargos(id))";
                break;

            case "estudiantes":
                createSQL = "CREATE TABLE estudiantes(id INT PRIMARY KEY, codigo VARCHAR(20), programa VARCHAR(100), promedio DOUBLE, " +
                        "FOREIGN KEY (id) REFERENCES personales(id))";
                break;

            case "estudiantesinscrito":
                createSQL = "CREATE TABLE estudiantesinscrito(id INT AUTO_INCREMENT PRIMARY KEY, id_estudiante int, " +
                        "FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id))";
                break;

            case "PersonalInscrito":
                createSQL = "CREATE TABLE PersonalInscrito(id INT AUTO_INCREMENT PRIMARY KEY, id_personal int, " +
                        "FOREIGN KEY (id_personal) REFERENCES personales(id))";
                break;

            case "listadoTodos":
                createSQL = "CREATE TABLE listadoTodos(id int AUTO_INCREMENT PRIMARY KEY, id_persona int, id_cargo int, " +
                        "FOREIGN KEY (id_persona) REFERENCES personales(id), FOREIGN KEY (id_cargo) REFERENCES cargos(id))";
                break;

            default:
                System.out.println("Tabla no reconocida: " + tableName);
                return;
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createSQL);
            System.out.println("Tabla " + tableName + " creada con éxito.");
        } catch (Exception e) {
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
