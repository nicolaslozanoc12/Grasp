package org.example.archivos;

import org.example.CrudInterfaz.CrudEstudiante;
import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Estudiante;
import org.example.modelo.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CrudEstudianteArchivo implements CrudEstudiante {
    private static final String FILE_NAME = "estudiantes.txt";
    private final CrudPersona crudPersona = new CrudPersonalArchivo();

    @Override
    public void insertar(Estudiante objeto) {
        crudPersona.insertar(objeto);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," +
                    objeto.getCodigo() + "," +
                    objeto.getPrograma() + "," +
                    objeto.getPromedio());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Estudiante> buscarTodos() {
        List<Estudiante> EstudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Persona persona =  crudPersona.buscarPorId(Integer.parseInt(parts[0]));
                EstudianteList.add(new Estudiante(Integer.parseInt(
                        parts[0]), persona.getNombre(),
                        persona.getApellidos(),
                        persona.getDireccion(),
                        parts[1],
                        parts[2],
                        Double.parseDouble(parts[3])
                        ));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return EstudianteList;
    }

    @Override
    public Estudiante buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    Persona persona =  crudPersona.buscarPorId(id);
                    return new Estudiante(Integer.parseInt(
                            parts[0]), persona.getNombre(),
                            persona.getApellidos(),
                            persona.getDireccion(),
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3])
                    );
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Estudiante objeto, int id) {
        crudPersona.actualizar(objeto, id);
        List<Estudiante> EstudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    EstudianteList.add(objeto);
                } else {
                    Persona persona =  crudPersona.buscarPorId(idAhora);
                    EstudianteList.add(new Estudiante(Integer.parseInt(parts[0]), persona.getNombre(), persona.getApellidos(), persona.getDireccion(), parts[1], parts[2], Double.parseDouble(parts[3])));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        InsertarListaEstudiante(EstudianteList);

    }

    @Override
    public void eliminar(int id) {
        crudPersona.eliminar(id);
        List<Estudiante> EstudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora != id) {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    EstudianteList.add(new Estudiante(Integer.parseInt(parts[0]),
                            persona.getNombre(),
                            persona.getApellidos(),
                            persona.getDireccion(),
                            parts[1],
                            parts[2],
                            Double.parseDouble(parts[3])));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        InsertarListaEstudiante(EstudianteList);

    }

    private void InsertarListaEstudiante(List<Estudiante> EstudianteList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Estudiante e : EstudianteList) {
                writer.write(e.getId() + "," +
                        e.getCodigo() + "," +
                        e.getPromedio() + "," +
                        e.getPromedio());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

}
