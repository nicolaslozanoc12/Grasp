package org.example.archivos;

import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudPais;
import org.example.modelo.Departamento;
import org.example.modelo.Pais;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudDepartamentoArchivo implements CrudDepartamento {
    private static final String FILE_NAME = "departamentos.txt";
    private static final Logger logger = Logger.getLogger(CrudDepartamentoArchivo.class.getName());
    private final CrudPais crudPais = new CrudPaisArchivo();

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
    public void insertar(Departamento objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Departamento(newId, objeto.getNombre(), objeto.getPais());  // Crear el departamento con el nuevo ID

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," + objeto.getNombre() + "," + objeto.getPais().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Departamento> buscarTodos() {
        List<Departamento> departamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[2]));
                departamentos.add(new Departamento(Integer.parseInt(parts[0]), parts[1], pais));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return departamentos;
    }

    @Override
    public Departamento buscarPorNombre(String nombre) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(nombre)) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[2]));
                    return new Departamento(Integer.parseInt(parts[0]), parts[1], pais);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public Departamento buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[2]));
                    return new Departamento(id, parts[1], pais);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Departamento objeto, int id) {
        List<Departamento> departamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    departamentos.add(objeto);
                } else {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[2]));
                    departamentos.add(new Departamento(idAhora, parts[1], pais));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaDepartamento(departamentos);
    }

    @Override
    public void eliminar(int id) {
        List<Departamento> departamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[2]));
                    departamentos.add(new Departamento(Integer.parseInt(parts[0]), parts[1], pais));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaDepartamento(departamentos);
    }

    private void insertarListaDepartamento(List<Departamento> departamentos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Departamento d : departamentos) {
                writer.write(d.getId() + "," + d.getNombre() + "," + d.getPais().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
