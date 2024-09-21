package org.example.archivos;
import java.io.*;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Direccion;
import org.example.modelo.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CrudPersonalArchivo implements CrudPersona {
    private static final String FILE_NAME = "persona.txt";
    CrudDireccion crudDireccion = new CrudDireccionArchivo();
    @Override
    public void insertar(Persona objeto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," +
                    objeto.getNombre() + "," +
                    objeto.getApellidos() + "," +
                    objeto.getDireccion().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

    }

    @Override
    public List<Persona> buscarTodos() {
        List<Persona> personalList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                personalList.add(new Persona(Integer.parseInt(parts[0]), parts[1], parts[2], direccion));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return personalList;
    }

    @Override
    public Persona buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    return new Persona(id, parts[1], parts[2], direccion);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Persona objeto, int id) {
        List<Persona> personalList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    personalList.add(objeto);
                } else {
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    personalList.add(new Persona(id, parts[1], parts[2], direccion));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        InsertarListaPersonal(personalList);

    }

    @Override
    public void eliminar(int id) {
        List<Persona> personalList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora != id) {
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    personalList.add(new Persona(Integer.parseInt(parts[0]), parts[1], parts[2], direccion));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        InsertarListaPersonal(personalList);

    }

    private void InsertarListaPersonal(List<Persona> personalList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Persona p : personalList) {
                writer.write(p.getId() + "," +
                        p.getNombre() + "," +
                        p.getApellidos() + "," +
                        p.getDireccion().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public Persona buscarPorNombreyApellido(String nombre, String apellido) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(nombre) && parts[2].equalsIgnoreCase(apellido)) {
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    return new Persona(Integer.parseInt(parts[0]), parts[1], parts[2], direccion);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }
}
