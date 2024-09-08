package org.example.ImplDAO;

import org.example.DAO.DepartamentoDAO;
import org.example.modelo.Departamento;
import org.example.modelo.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DepartamentoDAOImpl implements DepartamentoDAO {
    @Override
    public void insertar(Departamento objeto) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO emb.departamentos (nombre,id_pais) VALUES ( ? , ?)");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setInt(2,objeto.getPais().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }

    @Override
    public Departamento buscarPorNombre(String nombre) {
        Departamento departamento = null;
        Pais pais = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.departamentos WHERE nombre = ?");
            preparedStatement.setString(1, nombre);
            ResultSet departamentoResult = preparedStatement.executeQuery();
            if (departamentoResult.next()) {
                int id = departamentoResult.getInt("id");
                String nombreDepartamento = departamentoResult.getString("nombre");
                int idPais = departamentoResult.getInt("id_pais");
                PaisDAOImpl paisDAO = new PaisDAOImpl();
                pais = paisDAO.buscarPorId(idPais);
                departamento = new Departamento(id,nombreDepartamento,pais);
            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return departamento;
    }

    @Override
    public Departamento buscarPorId(int id) {
        Departamento departamento = null;
        Pais pais = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.departamentos WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet departamentoResult = preparedStatement.executeQuery();
            if (departamentoResult.next()) {
                String nombreDepartamento = departamentoResult.getString("nombre");
                int idPais = departamentoResult.getInt("id_pais");
                PaisDAOImpl paisDAO = new PaisDAOImpl();
                pais = paisDAO.buscarPorId(idPais);
                departamento = new Departamento(id,nombreDepartamento,pais);
            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return departamento;

    }

    @Override
    public List<Departamento> buscarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        Pais pais = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.departamentos");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombreDepartamento = resultSet.getString("nombre");
                int id_pais = resultSet.getInt("id_pais");
                PaisDAOImpl paisDAOimpl = new PaisDAOImpl();
                pais = paisDAOimpl.buscarPorId(id_pais);
                Departamento departamento = new Departamento(id,nombreDepartamento,pais);
                departamentos.add(departamento);
            }
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return departamentos;
    }

    @Override
    public void actualizar(Departamento objeto, int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("update emb.departamentos set nombre = ?,id_pais=? where id = ? ");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setInt(2, objeto.getPais().getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        }catch (Exception exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }

    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("delete from emb.departamentos where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Departamento eliminado con exito");
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }

    }
}
