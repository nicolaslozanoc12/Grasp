package org.example.CrudBaseDeDatos;

import org.example.Config.ConfigLoader;
import org.example.CrudInterfaz.CrudEstudiante;
import org.example.modelo.Direccion;
import org.example.modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EstudianteBaseDeDatos implements CrudEstudiante {
    private Connection conexion;
    private ConfigLoader configLoader;

    public EstudianteBaseDeDatos(ConfigLoader configLoader) {
        // Configurar el tipo de almacenamiento
        configLoader.configureStorage();
        // Obtener la conexión desde el DatabaseManager
        this.conexion = configLoader.getDbManager().getConnection();
    }
    private final PersonalBaseDeDatos personalDAO = new PersonalBaseDeDatos(configLoader);
    private final DireccionBaseDeDatos direccionDAO = new DireccionBaseDeDatos(configLoader);
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
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }

    @Override
    public Estudiante buscarPorId(int id) {
        Estudiante estudiante=null;
        try {
            String consulta = "SELECT p.id, p.nombre, p.apellidos, p.id_direccion, e.codigo, e.programa, e.promedio " +
                    "FROM emb.personales p " +
                    "JOIN emb.estudiantes e ON p.id = e.id " +
                    "WHERE p.id = ?";
            PreparedStatement preparedStatement=conexion.prepareStatement(consulta);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idResult = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellidos");
                int idDireccion = resultSet.getInt("id_direccion");
                String codigo=resultSet.getString("codigo");
                String programa=resultSet.getString("programa");
                Double promedio=resultSet.getDouble("promedio");
                Direccion direccion=direccionDAO.buscarPorId(idDireccion);
                estudiante=new Estudiante(idResult,nombre,apellido,direccion,codigo,programa,promedio);
            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return estudiante;
    }

    @Override
    public List<Estudiante> buscarTodos() {
        List<Estudiante> estudiantes= new ArrayList<>();
        try {
            String consulta = "SELECT p.id, p.nombre, p.apellidos, p.id_direccion, e.codigo, e.programa, e.promedio " +
                    "FROM emb.personales p " +
                    "JOIN emb.estudiantes e ON p.id = e.id ";
            PreparedStatement preparedStatement=conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idResult = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellidos");
                int idDireccion = resultSet.getInt("id_direccion");
                String codigo=resultSet.getString("codigo");
                String programa=resultSet.getString("programa");
                Double promedio=resultSet.getDouble("promedio");
                Direccion direccion=direccionDAO.buscarPorId(idDireccion);
                Estudiante estudiante=new Estudiante(idResult,nombre,apellido,direccion,codigo,programa,promedio);
                estudiantes.add(estudiante);
            }
        }catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return estudiantes;
    }

    @Override
    public void actualizar(Estudiante objeto, int id) {
        try {
            personalDAO.actualizar(objeto,id);
            PreparedStatement ps = conexion.prepareStatement("UPDATE emb.estudiantes SET codigo = ?, programa = ?, promedio = ? WHERE id = ?");
            ps.setString(1, objeto.getCodigo());
            ps.setString(2, objeto.getPrograma());
            ps.setDouble(3, objeto.getPromedio());
            ps.setInt(4, objeto.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }

    }

    @Override
    public void eliminar(int id) {
        try {
            String sqlEstudiante = "DELETE FROM emb.estudiantes WHERE id = ?";
            PreparedStatement psEstudiante = conexion.prepareStatement(sqlEstudiante);
            psEstudiante.setInt(1, id);
            psEstudiante.executeUpdate();
            personalDAO.eliminar(id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }
}
