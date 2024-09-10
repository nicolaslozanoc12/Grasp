package org.example;

import org.example.conexion.ConexionDatabase;
import org.example.controlador.*;
import org.example.modelo.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ConexionDatabase conexion = new ConexionDatabase();
        conexion.getConectionDataBaseH2();

        // Creando instancias de los controladores
        PersonalControlador personaControlador = new PersonalControlador();
        EmpleadoControlador empleadoControlador = new EmpleadoControlador();
        CargoControlador cargoControlador = new CargoControlador();
        EstudianteControlador estudianteControlador = new EstudianteControlador();
        DireccionControlador direccionControlador = new DireccionControlador();
        PersonalInscritoControlador personaInscritoControlador = new PersonalInscritoControlador();

        // ============ Persona CRUD ==============
        Persona persona = new Persona(1, "Juan", "Pérez", null); // Dirección aún no está definida
        personaControlador.agregarPersonal(persona);
        personaControlador.listarPersonal();
        Persona personaActualizada = new Persona(persona.getId(), "Juan Carlos", persona.getApellidos(), persona.getDireccion());
        personaControlador.actualizarPersonal(personaActualizada, 1);
        personaControlador.eliminarPersonal(1);

        // ============ Cargo CRUD ==============
        cargoControlador.agregarCargo(new Cargo(101, "Director de TI"));
        cargoControlador.listarCargos();
        Cargo cargoActualizado = new Cargo(101, "Director de Operaciones");
        cargoControlador.actualizarCargo(cargoActualizado, 101);
        cargoControlador.eliminarCargo(101);


        // ============ Empleado CRUD ==============
        Cargo cargo = new Cargo(101, "Gerente");
        empleadoControlador.agregarEmpleado(new Empleado(1,"Nicolas","Lozano",null,cargo, 5000.0));
        empleadoControlador.listarEmpleado();
        Empleado empleado = empleadoControlador.obtenerEmpleadoPorId(1);
        empleado.setSalario(5500.0);
        empleadoControlador.actualizarEmpleado(empleado, 1);
        empleadoControlador.eliminarEmpleado(1);

        // ============ Estudiante CRUD ==============
        Estudiante estudiante = new Estudiante(1, "2023456", "Ingeniería", null,"123","Ing sistemas",4.5);
        estudianteControlador.agregarEstudiante(estudiante);
        estudianteControlador.listarEstudiantes();
        Estudiante estudianteActualizado = new Estudiante(estudiante.getId(),"Nicole", "Bernal",null,estudiante.getCodigo(),"Ingeniería de Software", estudiante.getPromedio());
        estudianteControlador.actualizarEstudiante(estudianteActualizado, 1);
        estudianteControlador.eliminarEstudiante(1);

        // ============ Direccion CRUD ==============
        // Agregar Direccion
        Pais pais = new Pais(1, "Colombia");
        Departamento departamento = new Departamento(1, "Meta", pais);
        Municipio municipio = new Municipio(1, "Villavicencio", departamento);
        Direccion direccion = new Direccion(1, municipio, departamento, pais, "Calle 10", "Carrera 5", "4.1534, -73.6353", "Cerca del parque central");

        direccionControlador.agregarDireccion(direccion);
        direccionControlador.listarDirecciones();
        Direccion direccionActualizada = new Direccion(direccion.getId(), direccion.getMunicipio(), direccion.getDepartamento(), direccion.getPais(), "Calle 11", direccion.getCarrera(), direccion.getCoordenada(), direccion.getDescripcion());
        direccionControlador.actualizarDireccion(direccionActualizada, 1);
        direccionControlador.eliminarDireccion(1);

        // ============ Persona Inscrito CRUD ==============
        PersonalInscrito personaInscrita = new PersonalInscrito(1, empleado);
        personaInscritoControlador.agregarPersonalInscrito(personaInscrita);
        personaInscritoControlador.listarPersonalInscrito();
        personaInscritoControlador.actualizarPersonalInscrito(personaInscrita, 1);
        personaInscritoControlador.eliminarPersonalInscrito(1);


    }
}