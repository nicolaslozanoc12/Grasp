package org.example.CrudBaseDeDatos;

import org.example.CrudInterfaz.CrudDireccion;
import org.example.modelo.Departamento;
import org.example.modelo.Direccion;
import org.example.modelo.Municipio;
import org.example.modelo.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class DireccionBaseDeDatos implements CrudDireccion {
    PaisBaseDeDatos paisDAO = new PaisBaseDeDatos();
    DepartamentoBaseDeDatos departamentoDAO = new DepartamentoBaseDeDatos();
    MunicipioBaseDeDatos municipioDAO = new MunicipioBaseDeDatos();
    @Override
    public void insertar(Direccion objeto) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("INSERT INTO emb.direcciones (id_pais,id_departamento,id_municipio,calle,carrera,coordenada,descripcion) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,objeto.getPais().getId());
            preparedStatement.setInt(2,objeto.getDepartamento().getId());
            preparedStatement.setInt(3,objeto.getMunicipio().getId());
            preparedStatement.setString(4,objeto.getCalle());
            preparedStatement.setString(5,objeto.getCarrera());
            preparedStatement.setString(6,objeto.getCoordenada());
            preparedStatement.setString(7,objeto.getDescripcion());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            logger.log(Level.SEVERE, "error SQL", e);
        }
    }

    @Override
    public Direccion buscarPorId(int id) {
        Direccion direccion=null;
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.direcciones WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet departamentoResult = preparedStatement.executeQuery();
            if (departamentoResult.next()) {
                int idDireccion = departamentoResult.getInt("id");
                int idPais=departamentoResult.getInt("id_pais");
                int idDepartamento=departamentoResult.getInt("id_departamento");
                int idMunicipio=departamentoResult.getInt("id_municipio");
                String calle=departamentoResult.getString("calle");
                String carrera=departamentoResult.getString("carrera");
                String coordena=departamentoResult.getString("coordenada");
                String descripcion=departamentoResult.getString("descripcion");
                Pais pais= paisDAO.buscarPorId(idPais);
                Municipio municipio=municipioDAO.buscarPorId(idMunicipio);
                Departamento departamento=departamentoDAO.buscarPorId(idDepartamento);
                direccion=new Direccion(idDireccion,municipio,departamento,pais,calle,carrera,coordena,descripcion);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error SQL", e);
        }
        return direccion;
    }

    @Override
    public List<Direccion> buscarTodos() {
        List<Direccion> direcciones=new ArrayList<>();
        Pais pais = null;
        Departamento departamento = null;
        Municipio municipio = null;
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("SELECT * FROM emb.direcciones");
            ResultSet direccionResult=preparedStatement.executeQuery();
            while (direccionResult.next()){
                int id=direccionResult.getInt("id");
                int idPais=direccionResult.getInt("id_pais");
                int idDepartamento=direccionResult.getInt("id_departamento");
                int idMunicipio=direccionResult.getInt("id_municipio");
                pais = paisDAO.buscarPorId(idPais);
                departamento = departamentoDAO.buscarPorId(idDepartamento);
                municipio = municipioDAO.buscarPorId(idMunicipio);
                String calle=direccionResult.getString("calle");
                String carrera=direccionResult.getString("carrera");
                String coordenada=direccionResult.getString("coordenada");
                String descripcion=direccionResult.getString("descripcion");
                Direccion direccionSet=new Direccion(id,municipio,departamento,pais,calle,carrera,coordenada,descripcion);
                direcciones.add((direccionSet));
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error SQL", e);
        }
        return direcciones;
    }

    @Override
    public void actualizar(Direccion objeto, int id) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("UPDATE emb.direcciones SET id_pais = ?, id_departamento = ?, id_municipio = ?, calle = ?, carrera = ?, coordenada = ?, descripcion = ? WHERE id = ?");
            preparedStatement.setInt(1,objeto.getPais().getId());
            preparedStatement.setInt(2,objeto.getDepartamento().getId());
            preparedStatement.setInt(3,objeto.getMunicipio().getId());
            preparedStatement.setString(4,objeto.getCalle());
            preparedStatement.setString(5,objeto.getCarrera());
            preparedStatement.setString(6,objeto.getCoordenada());
            preparedStatement.setString(7,objeto.getDescripcion());
            preparedStatement.setInt(8,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "error SQL", e);
        }
    }
    @Override
    public void eliminar(int id) {
        try{
            PreparedStatement preparedStatement=conexion.prepareStatement("DELETE FROM emb.direcciones WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            logger.log(Level.SEVERE, "error SQL", e);
        }
    }
}
