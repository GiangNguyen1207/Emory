package com.example.emory;

import java.util.ArrayList;
import java.util.List;

public class TodoListSingleton {
    private static final TodoListSingleton listInstance = new TodoListSingleton();

    public static TodoListSingleton getInstance() {
        return listInstance;
    }

    private ArrayList<Todo> todolist;
    private TodoListSingleton() {
        this.todolist = new ArrayList<>();
    }

    public List<Todo> getAllTodo() {
        return this.todolist;
    }

  
    public Todo getTodo(int indexOfTodo) {
        return this.todolist.get(indexOfTodo);
    }

    public void addActivity(Todo todo) {
        this.todolist.add(todo);
    }

    public String toString() {
        return "name";
    }

    public void remove(Todo todo) {
        this.todolist.remove(todo);
    }
}

