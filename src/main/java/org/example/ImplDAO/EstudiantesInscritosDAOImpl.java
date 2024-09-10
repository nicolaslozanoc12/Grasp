package org.example.ImplDAO;

import org.example.DAO.EstudiantesInscritosDAO;
import org.example.modelo.Departamento;
import org.example.modelo.Estudiante;
import org.example.modelo.EstudiantesInscritos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EstudiantesInscritosDAOImpl implements EstudiantesInscritosDAO {
    EstudianteDAOImpl estudiantesDAO=new EstudianteDAOImpl();
    @Override
    public void insertar(EstudiantesInscritos objeto) {
        try{
            String sentenciaInsertarEstudinatesInscritos="INSER INTO emb.estudiantesinscrito (id,id_estudiante) VALUES (?,?)";
            PreparedStatement preparedStatement=conexion.prepareStatement(sentenciaInsertarEstudinatesInscritos);
            preparedStatement.setInt(1,objeto.getId());
            preparedStatement.setInt(2, objeto.getEstudiante().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al insertar", e);
        }

    }

    @Override
    public EstudiantesInscritos buscarPorNombre(String nombre) {
        EstudiantesInscritos estudiantesInscritos = null;
        Estudiante estudiante = null;
        try {
            String sentenciaBuscarEstudinateInscrito = "SELECT * FROM emb.estudiantesinscrito INNER JOIN emb.estudiantes ON estudiantes.id = estudiantesinscrito.id WHERE emb.estudiantes.nombre=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaBuscarEstudinateInscrito);
            preparedStatement.setString(1, nombre);
            ResultSet estudiantesResult = preparedStatement.executeQuery();
            if (estudiantesResult.next()) {
                int id = estudiantesResult.getInt("id");
                int idEstudiante = estudiantesResult.getInt("id_estudiante");
                estudiante = estudiantesDAO.buscarPorId(idEstudiante);
                estudiantesInscritos = new EstudiantesInscritos(id, estudiante);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return estudiantesInscritos;
    }

    @Override
    public EstudiantesInscritos buscarPorId(int id) {
        EstudiantesInscritos estudiantesInscritos =new EstudiantesInscritos();
        Estudiante estudiante = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.estudiantesinscrito WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet estudiantesInscritoResult = preparedStatement.executeQuery();
            if (estudiantesInscritoResult.next()) {
              int estudiantesInscritoId=estudiantesInscritoResult.getInt("id");
              int idEstudiante=estudiantesInscritoResult.getInt("id_estudiante");
                estudiante = estudiantesDAO.buscarPorId(idEstudiante);
              estudiantesInscritos=new EstudiantesInscritos(estudiantesInscritoId,estudiante);

            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return  estudiantesInscritos;
    }

    @Override
    public List<EstudiantesInscritos> buscarTodos() {
        List<EstudiantesInscritos> estudiantesInscritos =new ArrayList<>();
        Estudiante estudiante = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.estudiantesinscrito");
            ResultSet estudiantesInscritoResult = preparedStatement.executeQuery();
            while (estudiantesInscritoResult.next()) {
                int estudiantesInscritoId=estudiantesInscritoResult.getInt("id");
                int idEstudiante=estudiantesInscritoResult.getInt("id_estudiante");
                estudiante = estudiantesDAO.buscarPorId(idEstudiante);
                EstudiantesInscritos estudiantesInscritos1=new EstudiantesInscritos(estudiantesInscritoId,estudiante);
                estudiantesInscritos.add(estudiantesInscritos1);

            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return estudiantesInscritos;
    }

    @Override
    public void actualizar(EstudiantesInscritos objeto, int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE * FROM emb.estudiantesinscrito SET id = ?, id_estudiante = ? WHERE id= ?");
            preparedStatement.setInt(1, objeto.getId());
            preparedStatement.setInt(2, objeto.getEstudiante().getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("DELETE * FROM emb.estudiantesinscrito WHERE id= ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            logger.log(Level.SEVERE, "error al eliminar", exception);
        }

    }
}
