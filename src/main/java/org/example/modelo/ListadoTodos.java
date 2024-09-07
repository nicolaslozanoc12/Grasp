package org.example.modelo;

import java.util.List;

public class ListadoTodos {
    private List<Todos> todosList;

    public int cantidad(){
        return todosList.size();
    }
    public void adicionar(Todos todos){
        todosList.add(todos);
    }
    public String consultar(int id){
        Todos todo = todosList.get(id);
        return todo.toString();
    }
    public  String mostrar(int id){
        Todos todo = todosList.get(id);
        return todo.toString();
    }
}
