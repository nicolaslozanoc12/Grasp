package org.example.archivos;

import org.example.CrudInterfaz.CrudPais;
import org.example.modelo.Pais;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudPaisArchivo implements CrudPais {
    private static final String FILE_PAIS = "paises.txt";
    private static final Logger logger = Logger.getLogger(CrudPaisArchivo.class.getName());

    // Obtener el último ID utilizado en el archivo
    private int getLastId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                int currentId = Integer.parseInt(campos[0]);
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
    public void insertar(Pais objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Pais(newId, objeto.getNombre());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PAIS, true))) {
            writer.write(objeto.getId() + "," + objeto.getNombre());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Pais> buscarTodos() {
        List<Pais> paises = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                paises.add(new Pais(Integer.parseInt(campos[0]), campos[1]));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al buscar todos los paises", e);
        }
        return paises;
    }

    @Override
    public Pais buscarPorNombre(String nombre) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos[1].equalsIgnoreCase(nombre)) {
                    return new Pais(Integer.parseInt(campos[0]), campos[1]);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public Pais buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                if (Integer.parseInt(campos[0]) == id) {
                    return new Pais(id, campos[1]);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Pais objeto, int id) {
        List<Pais> paises = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                if (Integer.parseInt(campos[0]) == id) {
                    paises.add(objeto);
                } else {
                    paises.add(new Pais(Integer.parseInt(campos[0]), campos[1]));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        insertarListaPais(paises);
    }

    @Override
    public void eliminar(int id) {
        List<Pais> paises = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    paises.add(new Pais(Integer.parseInt(parts[0]), parts[1]));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        insertarListaPais(paises);
    }

    private void insertarListaPais(List<Pais> paises) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PAIS))) {
            for (Pais p : paises) {
                writer.write(p.getId() + "," + p.getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
