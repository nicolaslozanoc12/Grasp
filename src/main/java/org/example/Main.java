package org.example;

import org.example.conexion.ConexionDatabase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ConexionDatabase conexion = new ConexionDatabase();
        conexion.getConectionDataBaseH2();
    }
}