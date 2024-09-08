package org.example.ImplDAO;

import org.example.DAO.PaisDAO;
import org.example.modelo.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PaisDAOImpl implements PaisDAO {

    public PaisDAOImpl() {
    }

    @Override
    public void insertar(Pais objeto) {
        try {
            String sentenciaInsertarPais = "INSERT INTO emb.paises (nombre) values(?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaInsertarPais);
            preparedStatement.setString(1,objeto.getNombre());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al crear", exception);
        }

    }

    @Override
    public Pais buscarPorNombre(String nombre) {
        Pais pais = null;
        try {
            String sentenciaBuscarpaisPorNombre = "SELECT * FROM emb.paises WHERE nombre = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaBuscarpaisPorNombre);
            preparedStatement.setString(1,nombre);
            ResultSet paisResult = preparedStatement.executeQuery();
            if (paisResult.next()) {
                int id = paisResult.getInt("id");
                String nombrePais = paisResult.getString("nombre");
                pais = new Pais(id,nombrePais);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return pais;
    }

    @Override
    public Pais buscarPorId(int id) {
        Pais pais = null;
        try {
            String sentenciaBuscarPaisPorId = "Select * from emb.paises where id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaBuscarPaisPorId);
            preparedStatement.setInt(1,id);
            ResultSet paisResult = preparedStatement.executeQuery();
            if (paisResult.next()) {
                String nombrePais = paisResult.getString("nombre");
                pais = new Pais(id,nombrePais);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return pais;
    }

    @Override
    public List<Pais> buscarTodos() {
        List<Pais> paises = new ArrayList<>();
        try {
            String sentenciaObtenerPaises = "SELECT * FROM emb.paises ";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaObtenerPaises);
            ResultSet paisResult = preparedStatement.executeQuery();
            while (paisResult.next()) {
                int id = paisResult.getInt("id");
                String nombrePais = paisResult.getString("nombre");
                Pais pais = new Pais(id, nombrePais);
                paises.add(pais);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return paises;
    }

    @Override
    public void actualizar(Pais objeto, int id) {
        try {
            String sentenciaModificarPais = "UPDATE emb.paises SET nombre = ? WHERE id=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaModificarPais);
            preparedStatement.setString(1,objeto.getNombre());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al actualizar", exception);
        }

    }

    @Override
    public void eliminar(int id) {
        try {
            String sentenciaEliminarPais = "DELETE FROM emb.paises Where id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaEliminarPais);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al borrar", exception);
        }

    }
}
