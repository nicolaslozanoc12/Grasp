package org.example.ImplDAO;

import org.example.DAO.EstudianteDAO;
import org.example.DAO.PersonalDAO;
import org.example.modelo.Estudiante;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {
    private PersonalDAOImpl personalDAO = new PersonalDAOImpl();
    @Override
    public void insertar(Estudiante objeto) {
        try{
            personalDAO.insertar(objeto);
            PreparedStatement preparedStatement=conexion.prepareStatement("INSERT INTO emb.estudiantes (id,codigo,programa,promedio) VALUES (?,?,?,?)");
            preparedStatement.setInt(1, objeto.getId());
            preparedStatement.setString(2, objeto.getCodigo());
            preparedStatement.setString(3, objeto.getPrograma());
            preparedStatement.setDouble(4,objeto.getPromedio());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Estudiante buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Estudiante buscarPorId(int id) {

    }

    @Override
    public List<Estudiante> buscarTodos() {
        return null;
    }

    @Override
    public void actualizar(Estudiante objeto, int id) {

    }

    @Override
    public void eliminar(int id) {

    }
}
