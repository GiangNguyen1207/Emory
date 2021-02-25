package com.example.emory;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private static final TodoList listInstance = new TodoList();

    public static TodoList getInstance() {
        return listInstance;
    }

    private List<Todo> todolist;

    private TodoList() {
        this.todolist = new ArrayList<>();
        todolist.add(new Todo("Math homework", "25/2/2021"));
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
}

