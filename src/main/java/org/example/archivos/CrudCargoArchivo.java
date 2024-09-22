package org.example.archivos;

import org.example.CrudInterfaz.CrudCargo;
import org.example.modelo.Cargo;
import org.example.modelo.Departamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CrudCargoArchivo implements CrudCargo {
    private static final String FILE_NAME="cargos.txt";
    private final CrudCargo crudCargo=new CrudCargoArchivo();
    @Override
    public void insertar(Cargo objeto) {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_NAME,true))){
            writer.write(objeto.getId() + "," + objeto.getNombre());
            writer.newLine();
        }catch (IOException e){
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Cargo> buscarTodos() {
        List<Cargo> cargos=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                cargos.add(new Cargo(Integer.parseInt(parts[0]), parts[1]));
            }
        }catch (IOException e){
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return cargos;
    }

    @Override
    public Cargo buscarPorNombre(String nombre) {
        try(BufferedReader reader=new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(nombre)) {
                    return new Cargo(Integer.parseInt(parts[0]),parts[1]);
                }
            }
        }catch (IOException e){
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;

    }

    @Override
    public Cargo buscarPorId(int id) {
        try(BufferedReader reader=new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    return new Cargo(id, parts[1]);
                }
            }
        }catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Cargo objeto, int id) {
        List<Cargo> cargos=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora=Integer.parseInt(parts[0]);
                if(idAhora==id){
                    cargos.add(objeto);
                }else{
                    cargos.add(new Cargo(id,parts[1]));
                }
            }
        }catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        insertarListaCargos(cargos);


    }

    @Override
    public void eliminar(int id) {
        List<Cargo> cargos=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    cargos.add(new Cargo(Integer.parseInt(parts[0]), parts[1]));
                }
            }
        }catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());;
        }
        insertarListaCargos(cargos);
    }
    private void insertarListaCargos(List<Cargo> cargos){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cargo c : cargos) {
                writer.write(c.getId() + "," + c.getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
