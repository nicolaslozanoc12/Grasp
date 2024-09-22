package org.example.archivos;

import org.example.CrudInterfaz.CrudEstudiante;
import org.example.CrudInterfaz.CrudEstudiantesInscritos;
import org.example.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CrudEstudiantesInscritosArchivo implements CrudEstudiantesInscritos {
    private static final String FILE_NAME = "estudiantesInscritos.txt";
    CrudEstudiante crudEstudiante = new CrudEstudianteArchivo();

    @Override
    public void insertar(EstudiantesInscritos objeto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," +
                    objeto.getEstudiante().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<EstudiantesInscritos> buscarTodos() {
        List<EstudiantesInscritos> EstudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                EstudianteList.add(new EstudiantesInscritos(Integer.parseInt(parts[0]),estudiante));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return EstudianteList;
    }

    @Override
    public EstudiantesInscritos buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[0]));
                    return new EstudiantesInscritos(Integer.parseInt(parts[0]),estudiante);
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
                    Estudiante estudiante= crudEstudiante.buscarPorId(Integer.parseInt(parts[2]));
                    estudiantesInscritosList.add(new EstudiantesInscritos(Integer.parseInt(parts[0]),estudiante));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaEstudiantesInscritos(estudiantesInscritosList);

    }
    @Override
    public void eliminar(int id) {
        List<EstudiantesInscritos> estudiantesIncritosList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Estudiante estudiante = crudEstudiante.buscarPorId(Integer.parseInt(parts[1]));
                    estudiantesIncritosList.add(new EstudiantesInscritos(Integer.parseInt(parts[0]),estudiante));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());;
        }

        insertarListaEstudiantesInscritos(estudiantesIncritosList);
    }

    private void insertarListaEstudiantesInscritos(List<EstudiantesInscritos> estudiantesInscritosList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (EstudiantesInscritos d : estudiantesInscritosList) {
                writer.write(d.getId() + "," + d.getEstudiante().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }


}
