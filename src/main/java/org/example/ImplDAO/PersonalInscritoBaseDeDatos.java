package org.example.ImplDAO;

import org.example.CrudInterfaz.CrudPersonalInscrito;
import org.example.modelo.Empleado;
import org.example.modelo.PersonalInscrito;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PersonalInscritoBaseDeDatos implements CrudPersonalInscrito {
    EmpleadoBaseDeDatos empleadoDAO=new EmpleadoBaseDeDatos();
    @Override
    public void insertar(PersonalInscrito objeto) {
        try{
            String sentenciaInsertarPersonalInscritos="INSERT INTO emb.personalinscrito (id,id_empleado) VALUES (?,?)";
            PreparedStatement preparedStatement=conexion.prepareStatement(sentenciaInsertarPersonalInscritos);
            preparedStatement.setInt(1,objeto.getId());
            preparedStatement.setInt(2, objeto.getEmpleado().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al insertar", e);
        }
    }

    @Override
    public PersonalInscrito buscarPorNombre(String nombre) {
        PersonalInscrito personalInscrito = new PersonalInscrito();
        Empleado empleado = null;
        try {
            String sentenciaBuscarPersonalInscrito = "SELECT * FROM emb.personalinscrito INNER JOIN emb.empleados ON empleados.id_persona = personalinscrito.id_empleado WHERE emb.empleados.nombre=?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaBuscarPersonalInscrito);
            preparedStatement.setString(1, nombre);
            ResultSet empleadosResult = preparedStatement.executeQuery();
            if (empleadosResult.next()) {
                int id = empleadosResult.getInt("id");
                int idEmpleado = empleadosResult.getInt("id_empleado");
                empleado = empleadoDAO.buscarPorId(idEmpleado);
                personalInscrito = new PersonalInscrito(id, empleado);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return personalInscrito;
    }

    @Override
    public PersonalInscrito buscarPorId(int id) {
        PersonalInscrito personalInscrito = new PersonalInscrito();
        Empleado empleado = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.personalinscrito WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet empleadosResult = preparedStatement.executeQuery();
            if (empleadosResult.next()) {
                int idPersonalInscrito = empleadosResult.getInt("id");
                int idEmpleado = empleadosResult.getInt("id_empleado");
                empleado = empleadoDAO.buscarPorId(idEmpleado);
                personalInscrito = new PersonalInscrito(idPersonalInscrito, empleado);
            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return  personalInscrito;
    }

    @Override
    public List<PersonalInscrito> buscarTodos() {
        List<PersonalInscrito> personalInscritos=new ArrayList<>();
        PersonalInscrito personalInscrito = new PersonalInscrito();
        Empleado empleado = null;
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("SELECT * FROM emb.personalinscrito");
            ResultSet empleadosResult = preparedStatement.executeQuery();
            if (empleadosResult.next()) {
                int idPersonalInscrito = empleadosResult.getInt("id");
                int idEmpleado = empleadosResult.getInt("id_empleado");
                empleado = empleadoDAO.buscarPorId(idEmpleado);
                personalInscrito = new PersonalInscrito(idPersonalInscrito, empleado);
                personalInscritos.add(personalInscrito);
            }
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
        return  personalInscritos;
    }

    @Override
    public void actualizar(PersonalInscrito objeto, int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE * FROM emb.personalinscrito  SET id = ?, id_empleado = ? WHERE id = ?");
            preparedStatement.setInt(1, objeto.getId());
            preparedStatement.setInt(2, objeto.getEmpleado().getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al buscar", exception);
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("DELETE * FROM emb.personalinscrito WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            logger.log(Level.SEVERE, "error al eliminar", exception);
        }
    }
}
