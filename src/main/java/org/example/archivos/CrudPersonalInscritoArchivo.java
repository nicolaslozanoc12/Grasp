package org.example.archivos;

import org.example.CrudInterfaz.CrudPersona;
import org.example.CrudInterfaz.CrudPersonalInscrito;
import org.example.modelo.Persona;
import org.example.modelo.PersonalInscrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CrudPersonalInscritoArchivo implements CrudPersonalInscrito {
    private static final String FILE_NAME = "personalInscrito.txt";
    CrudPersona crudPersona = new CrudPersonalArchivo();
    @Override
    public void insertar(PersonalInscrito objeto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(objeto.getId() + "," +
                    objeto.getPersona().getId());
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }
    @Override
    public List<PersonalInscrito> buscarTodos() {
        List<PersonalInscrito> personalInscritoList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Persona persona = crudPersona.buscarPorId(Integer.parseInt(parts[1]));
                personalInscritoList.add(new PersonalInscrito(Integer.parseInt(parts[0]),persona));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return personalInscritoList;
    }

    @Override
    public PersonalInscrito buscarPorId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    Persona persona = crudPersona.buscarPorId(idAhora);
                    return new PersonalInscrito(idAhora, persona);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(PersonalInscrito objeto, int id) {
        List<PersonalInscrito> personalInscritoList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int idAhora = Integer.parseInt(parts[0]);
                if (idAhora == id) {
                    personalInscritoList.add(objeto);
                } else {
                    Persona persona= crudPersona.buscarPorId(Integer.parseInt(parts[2]));
                    personalInscritoList.add(new PersonalInscrito(Integer.parseInt(parts[0]),persona));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }

        insertarListaPersonalInscrito(personalInscritoList);

    }
    @Override
    public void eliminar(int id) {
        List<PersonalInscrito> personalInscritoList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != id) {
                    Persona persona = crudPersona.buscarPorId(Integer.parseInt(parts[1]));
                    personalInscritoList.add(new PersonalInscrito(Integer.parseInt(parts[0]),persona));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());;
        }

        insertarListaPersonalInscrito(personalInscritoList);

    }

    private void insertarListaPersonalInscrito(List<PersonalInscrito> personalInscritoList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PersonalInscrito d : personalInscritoList) {
                writer.write(d.getId() + "," + d.getPersona().getId());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: ", e.getMessage());
        }
    }


}
