package org.example.archivos;

import java.io.*;
import org.example.CrudInterfaz.CrudDepartamento;
import org.example.CrudInterfaz.CrudDireccion;
import org.example.CrudInterfaz.CrudMunicipio;
import org.example.CrudInterfaz.CrudPais;
import org.example.modelo.Departamento;
import org.example.modelo.Direccion;
import org.example.modelo.Municipio;
import org.example.modelo.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudDireccionArchivo implements CrudDireccion {
    private static final String FILE_NAME = "direcciones.txt";
    private static final Logger logger = Logger.getLogger(CrudDireccionArchivo.class.getName());
    private CrudPais crudPais = new CrudPaisArchivo();
    private CrudDepartamento crudDepartamento = new CrudDepartamentoArchivo();
    private CrudMunicipio crudMunicipio = new CrudMunicipioArchivo();

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
    public void insertar(Direccion objeto) {
        // Incrementar el ID basado en el último ID
        int newId = getLastId() + 1;
        objeto = new Direccion(newId, objeto.getMunicipio(), objeto.getDepartamento(), objeto.getPais(), objeto.getCalle(), objeto.getCarrera(), objeto.getCoordenada(), objeto.getDescripcion());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," +
                    objeto.getMunicipio().getId() + "," +
                    objeto.getDepartamento().getId() + "," +
                    objeto.getPais().getId() + "," +
                    objeto.getCalle() + "," +
                    objeto.getCarrera() + "," +
                    objeto.getCoordenada() + "," +
                    objeto.getDescripcion());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public List<Direccion> buscarTodos() {
        List<Direccion> direcciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[3]));
                Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                Municipio municipio = crudMunicipio.buscarPorId(Integer.parseInt(parts[1]));
                direcciones.add(new Direccion(Integer.parseInt(parts[0]), municipio, departamento, pais, parts[4], parts[5], parts[6], parts[7]));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return direcciones;
    }

    @Override
    public Direccion buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[3]));
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    Municipio municipio = crudMunicipio.buscarPorId(Integer.parseInt(parts[1]));
                    return new Direccion(id, municipio, departamento, pais, parts[4], parts[5], parts[6], parts[7]);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Direccion objeto, int id) {
        List<Direccion> direcciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    direcciones.add(objeto);
                } else {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[3]));
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    Municipio municipio = crudMunicipio.buscarPorId(Integer.parseInt(parts[1]));
                    direcciones.add(new Direccion(idAhora, municipio, departamento, pais, parts[4], parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaDireccion(direcciones);
    }

    @Override
    public void eliminar(int id) {
        List<Direccion> direcciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[3]));
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    Municipio municipio = crudMunicipio.buscarPorId(Integer.parseInt(parts[1]));
                    direcciones.add(new Direccion(Integer.parseInt(parts[0]), municipio, departamento, pais, parts[4], parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaDireccion(direcciones);
    }

    private void insertarListaDireccion(List<Direccion> direcciones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Direccion d : direcciones) {
                writer.write(d.getId() + "," +
                        d.getMunicipio().getId() + "," +
                        d.getDepartamento().getId() + "," +
                        d.getPais().getId() + "," +
                        d.getCalle() + "," +
                        d.getCarrera() + "," +
                        d.getCoordenada() + "," +
                        d.getDescripcion());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }

    @Override
    public Direccion buscarPorCalleYCarrera(String calle, String carrera) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[4].equalsIgnoreCase(calle) && parts[5].equalsIgnoreCase(carrera)) {
                    Pais pais = crudPais.buscarPorId(Integer.parseInt(parts[3]));
                    Departamento departamento = crudDepartamento.buscarPorId(Integer.parseInt(parts[2]));
                    Municipio municipio = crudMunicipio.buscarPorId(Integer.parseInt(parts[1]));
                    return new Direccion(Integer.parseInt(parts[0]), municipio, departamento, pais, parts[4], parts[5], parts[6], parts[7]);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }
}
