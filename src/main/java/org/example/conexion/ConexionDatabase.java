package org.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDatabase {
    private static final Logger logger = Logger.getLogger(ConexionDatabase.class.getName());
    public String username = "sa";
    public String password = "";
    public String urlDB = "jdbc:h2:./base";
    public String Driver_DB = "org.h2.Driver";

    public Connection getConectionDataBase(){
        Connection connection = null;
        try {
            Class.forName(Driver_DB);
            connection = DriverManager.getConnection(urlDB,username,password);
            logger.info("conectado con exito");
        }   catch (ClassNotFoundException | SQLException exception) {
            logger.log(Level.SEVERE, "error al conectar la base de datoss", exception);
        }
        return connection;
    }


}
