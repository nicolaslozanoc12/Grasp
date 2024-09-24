package org.example.CrudBaseDeDatos;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudMunicipio;
import org.example.modelo.Departamento;
import org.example.modelo.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MunicipioBaseDeDatos implements CrudMunicipio {
    private Connection conexion;
    private ConfigLoader configLoader;

    public MunicipioBaseDeDatos(ConfigLoader configLoader) {
        // Configurar el tipo de almacenamiento
        configLoader.configureStorage();
        // Obtener la conexión desde el DatabaseManager
        this.conexion = configLoader.getDbManager().getConnection();
    }
    @Override
    public void insertar(Municipio objeto) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO emb.municipios (nombre,id_departamento) VALUES ( ? , ? )");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setInt(2,objeto.getDepartamento().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error SQL", exception);
        }
    }

    @Override
    public Municipio buscarPorNombre(String nombre) {
        Municipio municipio = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.municipios WHERE nombre = ?");
            preparedStatement.setString(1,nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int idMunicipio = resultSet.getInt("id");
                String nombreMunicipio = resultSet.getString("nombre");
                int departamentoId = resultSet.getInt("id_departamento");
                CrudDepartamentoBaseDeDatos departamentoDAO = new CrudDepartamentoBaseDeDatos(configLoader);
                Departamento departamento = departamentoDAO.buscarPorId(departamentoId);
                municipio = new Municipio(idMunicipio,nombreMunicipio, departamento);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error SQL", exception);
        }
        return municipio;
    }

    @Override
    public Municipio buscarPorId(int id) {
        Municipio municipio = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.municipios WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int idMunicipio = resultSet.getInt("id");
                String nombreMunicipio = resultSet.getString("nombre");
                int departamentoId = resultSet.getInt("id_departamento");
                CrudDepartamentoBaseDeDatos departamentoDAO = new CrudDepartamentoBaseDeDatos(configLoader);
                Departamento departamento = departamentoDAO.buscarPorId(departamentoId);
                municipio = new Municipio(idMunicipio,nombreMunicipio, departamento);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error SQL", exception);
        }
        return municipio;
    }

    @Override
    public List<Municipio> buscarTodos() {
        List<Municipio> municipios = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.municipios");
            ResultSet municipioSet = preparedStatement.executeQuery();
            while (municipioSet.next()) {
                int id = municipioSet.getInt("id");
                String nombre = municipioSet.getString("nombre");
                int idDepartamento = municipioSet.getInt("id_departamento");
                CrudDepartamentoBaseDeDatos departamentoDAO = new CrudDepartamentoBaseDeDatos(configLoader);
                Departamento departamento = departamentoDAO.buscarPorId(idDepartamento);
                Municipio municipio = new Municipio(id,nombre,departamento);
                municipios.add(municipio);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error SQL", exception);
        }
        return municipios;
    }

    @Override
    public void actualizar(Municipio objeto, int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE emb.municipios SET nombre = ?, id_departamento = ? WHERE id = ?");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setInt(2, objeto.getDepartamento().getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error SQL", exception);
        }

    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("DELETE FROM emb.municipios WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error SQL", exception);
        }

    }
}
