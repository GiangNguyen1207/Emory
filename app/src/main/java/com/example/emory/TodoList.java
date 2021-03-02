package com.example.emory;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private static final TodoList listInstance = new TodoList();

    public static TodoList getInstance() {
        return listInstance;
    }

    private ArrayList<Todo> todolist;
    private TodoList() {
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
}

