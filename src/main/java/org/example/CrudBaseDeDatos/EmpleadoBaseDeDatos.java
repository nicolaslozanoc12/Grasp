package org.example.CrudBaseDeDatos;

import org.example.CrudInterfaz.CrudEmpleado;
import org.example.modelo.Cargo;
import org.example.modelo.Empleado;
import org.example.modelo.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EmpleadoBaseDeDatos implements CrudEmpleado {
    CargoBaseDeDatos cargoDAO=new CargoBaseDeDatos();
    PersonalBaseDeDatos personalDAO=new PersonalBaseDeDatos();

    @Override
    public void insertar(Empleado objeto) {
        try{
            personalDAO.insertar(objeto);
            PreparedStatement preparedStatement=conexion.prepareStatement("INSERT INTO emb.empleados (id_cargo,salario,id_persona) VALUES (?,?,?)");
            preparedStatement.setInt(1, objeto.getCargo().getId());
            preparedStatement.setDouble(2, objeto.getSalario());
            preparedStatement.setInt(3, objeto.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }

    @Override
    public Empleado buscarPorId(int id) {
        Empleado empleado=null;
        try {
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.empleados WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idCargo=resultSet.getInt("id_cargo");
                double salario=resultSet.getDouble("salario");
                int idPersona=resultSet.getInt("id_persona");
                Persona persona= personalDAO.buscarPorId(idPersona);
                Cargo cargo= cargoDAO.buscarPorId(idCargo);
                empleado=new Empleado(id,persona.getNombre(),persona.getApellidos(),persona.getDireccion(),cargo,salario);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
        return empleado;
    }

    @Override
    public List<Empleado> buscarTodos() {
        List<Empleado> empleados=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.empleados");
            ResultSet empleadosResult=preparedStatement.executeQuery();
            while(empleadosResult.next()){
                int id=empleadosResult.getInt("id");
                int idCargo=empleadosResult.getInt("id_cargo");
                double salario=empleadosResult.getDouble("salario");
                int idPersona=empleadosResult.getInt("id_persona");
                Persona persona= personalDAO.buscarPorId(idPersona);
                Cargo cargo= cargoDAO.buscarPorId(idCargo);
                Empleado empleadoSet=new Empleado(id,persona.getNombre(),persona.getApellidos(),persona.getDireccion(),cargo,salario);
                empleados.add(empleadoSet);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }

        return empleados;
    }

    @Override
    public void actualizar(Empleado objeto, int id) {
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement("UPDATE emb.empleados SET id_cargo = ?, salario = ?,id_persona=? WHERE id = ?");
            preparedStatement.setInt(1, objeto.getCargo().getId());
            preparedStatement.setDouble(2, objeto.getSalario());
            preparedStatement.setInt(3, objeto.getId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "error al buscar", e);

        }
    }

    @Override
    public void eliminar(int id) {
        try {
            PreparedStatement preparedStatement=conexion.prepareStatement("DELETE FROM emb.empleados WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error al buscar", e);
        }
    }
}
