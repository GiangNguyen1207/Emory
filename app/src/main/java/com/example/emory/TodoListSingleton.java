package com.example.emory;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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
        this.todolist.add(new Todo("Homework", "8, March, 2021", "Done"));
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

