package org.example.CrudBaseDeDatos;

import org.example.CrudInterfaz.CrudCargo;

import org.example.modelo.Cargo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CargoBaseDeDatos implements CrudCargo {
    Cargo cargo;
    @Override
    public void insertar(Cargo objeto) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("INSERT INTO emb.cargos (nombre) VALUES (?)");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }

    @Override
    public Cargo buscarPorNombre(String nombre) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.cargos WHERE nombre = ?");
            preparedStatement.setString(1,nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreResult=resultSet.getString("nombre");
                cargo=new Cargo(id,nombreResult);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return cargo;
    }

    @Override
    public Cargo buscarPorId(int id) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.cargos WHERE id = ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idResult = resultSet.getInt("id");
                String nombreResult=resultSet.getString("nombre");
                cargo=new Cargo(idResult,nombreResult);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return cargo;
    }

    @Override
    public List<Cargo> buscarTodos() {
        List<Cargo> cargos= new ArrayList<>();
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.cargos");
            ResultSet cargosResult=preparedStatement.executeQuery();
            while(cargosResult.next()){
                int id=cargosResult.getInt("id");
                String nombre=cargosResult.getString("nombre");
                Cargo cargo=new Cargo(id,nombre);
                cargos.add(cargo);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return cargos;
    }

    @Override
    public void actualizar(Cargo objeto, int id) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("UPDATE emb.cargos SET nombre = ? WHERE id = ?");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }

    @Override
    public void eliminar(int id) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("DELETE FROM emb.cargos WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }
}
