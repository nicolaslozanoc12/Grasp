package org.example.archivos;

import org.example.CrudInterfaz.CrudEstudiante;
import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Estudiante;
import org.example.modelo.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudEstudianteArchivo implements CrudEstudiante {
    private static final String FILE_NAME = "estudiantes.txt";
    private static final Logger logger = Logger.getLogger(CrudEstudianteArchivo.class.getName());
    private final CrudPersona crudPersona = new CrudPersonalArchivo();

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
    public void insertar(Estudiante objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Estudiante(newId, objeto.getNombre(), objeto.getApellidos(), objeto.getDireccion(), objeto.getCodigo(), objeto.getPrograma(), objeto.getPromedio());

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
        List<Estudiante> estudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Persona persona = crudPersona.buscarPorId(Integer.parseInt(parts[0]));
                estudianteList.add(new Estudiante(Integer.parseInt(parts[0]),
                        persona.getNombre(),
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
        return estudianteList;
    }

    @Override
    public Estudiante buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    Persona persona = crudPersona.buscarPorId(id);
                    return new Estudiante(Integer.parseInt(parts[0]),
                            persona.getNombre(),
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
        List<Estudiante> estudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    estudianteList.add(objeto);
                } else {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    estudianteList.add(new Estudiante(Integer.parseInt(parts[0]),
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

        insertarListaEstudiante(estudianteList);
    }

    @Override
    public void eliminar(int id) {
        crudPersona.eliminar(id);
        List<Estudiante> estudianteList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora != id) {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    estudianteList.add(new Estudiante(Integer.parseInt(parts[0]),
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

        insertarListaEstudiante(estudianteList);
    }

    private void insertarListaEstudiante(List<Estudiante> estudianteList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Estudiante e : estudianteList) {
                writer.write(e.getId() + "," +
                        e.getCodigo() + "," +
                        e.getPrograma() + "," +
                        e.getPromedio());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
