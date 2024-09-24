package org.example.archivos;

import org.example.CrudInterfaz.CrudEstudiante;
import org.example.CrudInterfaz.CrudEstudiantesInscritos;
import org.example.modelo.Estudiante;
import org.example.modelo.EstudiantesInscritos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudEstudiantesInscritosArchivo implements CrudEstudiantesInscritos {
    private static final String FILE_NAME = "estudiantesInscritos.txt";
    private static final Logger logger = Logger.getLogger(CrudEstudiantesInscritosArchivo.class.getName());
    CrudEstudiante crudEstudiante = new CrudEstudianteArchivo();

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
    public void insertar(EstudiantesInscritos objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new EstudiantesInscritos(newId, objeto.getEstudiante());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," + objeto.getEstudiante().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<EstudiantesInscritos> buscarTodos() {
        List<EstudiantesInscritos> estudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                estudianteList.add(new EstudiantesInscritos(Integer.parseInt(parts[0]), estudiante));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return estudianteList;
    }

    @Override
    public EstudiantesInscritos buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                    return new EstudiantesInscritos(Integer.parseInt(parts[0]), estudiante);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(EstudiantesInscritos objeto, int id) {
        List<EstudiantesInscritos> estudiantesInscritosList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    estudiantesInscritosList.add(objeto);
                } else {
                    Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                    estudiantesInscritosList.add(new EstudiantesInscritos(idAhora, estudiante));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaEstudiantesInscritos(estudiantesInscritosList);
    }

    @Override
    public void eliminar(int id) {
        List<EstudiantesInscritos> estudiantesInscritosList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                    estudiantesInscritosList.add(new EstudiantesInscritos(Integer.parseInt(parts[0]), estudiante));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaEstudiantesInscritos(estudiantesInscritosList);
    }

    private void insertarListaEstudiantesInscritos(List<EstudiantesInscritos> estudiantesInscritosList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (EstudiantesInscritos estudianteInscrito : estudiantesInscritosList) {
                writer.write(estudianteInscrito.getId() + "," + estudianteInscrito.getEstudiante().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
