package org.example.archivos;

import org.example.CrudInterfaz.CrudCargo;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudEmpleado;
import org.example.CrudInterfaz.CrudPersona;
import org.example.modelo.Cargo;
import org.example.modelo.Direccion;
import org.example.modelo.Empleado;
import org.example.modelo.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudEmpleadoArchivo implements CrudEmpleado {
    private static final String FILE_NAME = "empleados.txt";
    private static final Logger logger = Logger.getLogger(CrudEmpleadoArchivo.class.getName());
    private final CrudPersona crudPersona = new CrudPersonalArchivo();
    private final CrudDireccion crudDireccion = new CrudDireccionArchivo();
    private final CrudCargo crudCargo = new CrudCargoArchivo();

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
    public void insertar(Empleado objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Empleado(newId, objeto.getNombre(), objeto.getApellidos(), objeto.getDireccion(), objeto.getCargo(), objeto.getSalario());

        crudPersona.insertar(objeto);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," + objeto.getNombre() + "," + objeto.getApellidos() + "," +
                    objeto.getDireccion().getId() + "," + objeto.getCargo().getId() + "," + objeto.getSalario());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Empleado> buscarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Persona persona = crudPersona.buscarPorId(Integer.parseInt(parts[0]));
                Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                Cargo cargo = crudCargo.buscarPorId(Integer.parseInt(parts[4]));
                empleados.add(new Empleado(Integer.parseInt(parts[0]), persona.getNombre(), persona.getApellidos(), direccion, cargo, Double.parseDouble(parts[5])));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return empleados;
    }

    @Override
    public Empleado buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    Persona persona = crudPersona.buscarPorId(id);
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    Cargo cargo = crudCargo.buscarPorId(Integer.parseInt(parts[4]));
                    return new Empleado(idAhora, persona.getNombre(), persona.getApellidos(), direccion, cargo, Double.parseDouble(parts[5]));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Empleado objeto, int id) {
        crudPersona.actualizar(objeto, id);
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    empleados.add(objeto);
                } else {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    Cargo cargo = crudCargo.buscarPorId(Integer.parseInt(parts[4]));
                    empleados.add(new Empleado(idAhora, persona.getNombre(), persona.getApellidos(), direccion, cargo, Double.parseDouble(parts[5])));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        insertarListaEmpleado(empleados);
    }

    @Override
    public void eliminar(int id) {
        crudPersona.eliminar(id);
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora != id) {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    Direccion direccion = crudDireccion.buscarPorId(Integer.parseInt(parts[3]));
                    Cargo cargo = crudCargo.buscarPorId(Integer.parseInt(parts[4]));
                    empleados.add(new Empleado(idAhora, persona.getNombre(), persona.getApellidos(), direccion, cargo, Double.parseDouble(parts[5])));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        insertarListaEmpleado(empleados);
    }

    private void insertarListaEmpleado(List<Empleado> empleados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Empleado e : empleados) {
                writer.write(e.getId() + "," +
                        e.getNombre() + "," +
                        e.getApellidos() + "," +
                        e.getDireccion().getId() + "," +
                        e.getCargo().getId() + "," +
                        e.getSalario());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
}
