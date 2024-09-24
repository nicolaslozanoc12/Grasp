package org.example.archivos;

import java.io.*;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Direccion;
import org.example.modelo.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudPersonalArchivo implements CrudPersona {
    private static final String FILE_NAME = "persona.txt";
    private static final Logger logger = Logger.getLogger(CrudPersonalArchivo.class.getName());
    CrudDireccion crudDireccion = new CrudDireccionArchivo();

    // Obtener el último ID utilizado en el archivo
    private int getLastId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);
                if (currentId > lastId) {
                    lastId = currentId;  // Actualizar el último ID encontrado
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return lastId;
    }

    @Override
    public void insertar(Persona objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Persona(newId, objeto.getNombre(), objeto.getApellidos(), objeto.getDireccion());

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
                    personalList.add(new Persona(idAhora, parts[1], parts[2], direccion));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaPersonal(personalList);
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
                    personalList.add(new Persona(idAhora, parts[1], parts[2], direccion));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaPersonal(personalList);
    }

    private void insertarListaPersonal(List<Persona> personalList) {
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
