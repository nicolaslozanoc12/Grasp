package org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class PersonalInscrito {
    private List<Persona> listado= new ArrayList<Persona>();
    public Persona adicionar(Persona persona){
        listado.add(persona);
        return persona;
    }

    public PersonalInscrito() {

    }

    public void imprimirListado(){
        for(Persona persona: listado){
            System.out.println(persona.toString());
        }
    }
}
