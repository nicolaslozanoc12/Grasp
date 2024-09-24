package org.example.archivos;

import java.io.*;
import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudMunicipio;
import org.example.modelo.Departamento;
import org.example.modelo.Municipio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudMunicipioArchivo implements CrudMunicipio {
    private static final String FILE_NAME = "municipios.txt";
    private static final Logger logger = Logger.getLogger(CrudMunicipioArchivo.class.getName());
    private final CrudDepartamento crudDepartamento = new CrudDepartamentoArchivo();

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
    public void insertar(Municipio objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Municipio(newId, objeto.getNombre(), objeto.getDepartamento());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," + objeto.getNombre() + "," + objeto.getDepartamento().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Municipio> buscarTodos() {
        List<Municipio> municipios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                municipios.add(new Municipio(Integer.parseInt(parts[0]), parts[1], departamento));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return municipios;
    }

    @Override
    public Municipio buscarPorNombre(String nombre) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equalsIgnoreCase(nombre)) {
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    return new Municipio(Integer.parseInt(parts[0]), parts[1], departamento);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public Municipio buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    return new Municipio(id, parts[1], departamento);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Municipio objeto, int id) {
        List<Municipio> municipios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    municipios.add(objeto);
                } else {
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    municipios.add(new Municipio(idAhora, parts[1], departamento));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaMunicipio(municipios);
    }

    @Override
    public void eliminar(int id) {
        List<Municipio> municipios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    municipios.add(new Municipio(Integer.parseInt(parts[0]), parts[1], departamento));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaMunicipio(municipios);
    }

    private void insertarListaMunicipio(List<Municipio> municipios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Municipio m : municipios) {
                writer.write(m.getId() + "," + m.getNombre() + "," + m.getDepartamento().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
