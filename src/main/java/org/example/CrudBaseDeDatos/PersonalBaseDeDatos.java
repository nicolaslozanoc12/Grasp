package org.example.CrudBaseDeDatos;

import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Direccion;
import org.example.modelo.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PersonalBaseDeDatos implements CrudPersona {
    DireccionBaseDeDatos direccionDAO = new DireccionBaseDeDatos();
    @Override
    public void insertar(Persona objeto) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO emb.personales (nombre,apellidos,id_direccion) VALUES (?,?,?)");
            preparedStatement.setString(1, objeto.getNombre());
            preparedStatement.setString(2, objeto.getApellidos());
            preparedStatement.setInt(3, objeto.getDireccion().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                objeto.setId(rs.getInt("id"));  // Obtiene el ID generado para la persona
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }


    public Persona buscarPorNombreyApellido(String nombre, String apellido) {
        Persona persona = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.personales WHERE nombre=? AND apellido=?");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idDireccion = resultSet.getInt("id_direccion");
                Direccion direccion = direccionDAO.buscarPorId(idDireccion);
                persona = new Persona(id, nombre, apellido, direccion);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }

        return persona;
    }

    @Override
    public Persona buscarPorId(int id) {
        Persona persona = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.personales WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellidos");
                int idDireccion = resultSet.getInt("id_direccion");
                Direccion direccion = direccionDAO.buscarPorId(idDireccion);
                persona = new Persona(id, nombre, apellido, direccion);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }

        return persona;
    }

    @Override
    public List<Persona> buscarTodos() {
        List<Persona> personas = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.personales");
            ResultSet personasResult=preparedStatement.executeQuery();
            while(personasResult.next()){
                int id=personasResult.getInt("id");
                String nombre=personasResult.getString("nombre");
                String apellidos=personasResult.getString("apellidos");
                int idDireccion=personasResult.getInt("id_direccion");
                Direccion direccion= direccionDAO.buscarPorId(idDireccion);
                Persona persona=new Persona(id,nombre,apellidos,direccion);
                personas.add(persona);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return personas;
    }

    @Override
    public void actualizar(Persona objeto, int id) {
        try {
            PreparedStatement preparedStatement=conexion.prepareStatement("UPDATE emb.personales SET nombre = ?, apellidos = ?, id_direccion = ? WHERE id = ?");
            preparedStatement.setString(1,objeto.getNombre());
            preparedStatement.setString(2,objeto.getApellidos());
            preparedStatement.setInt(3,objeto.getDireccion().getId());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement=conexion.prepareStatement("Delete FROM emb.personales WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }
}
