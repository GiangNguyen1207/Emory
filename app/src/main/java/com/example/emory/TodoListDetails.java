package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TodoListDetails extends AppCompatActivity {
    ArrayList<TodoList> todo3 = new ArrayList<>();

    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_details);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String dataReceived = sharedPreferences.getString("todolist", String.valueOf(new ArrayList<TodoList>()));
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TodoList>>() {
        }.getType();
        todo3 = gson.fromJson(dataReceived, type);
        //TodoList todoList = TodoList.getInstance();
        Bundle b = getIntent().getExtras();
        int indexOfTodo = b.getInt("indexOfTodo");

        ((TextView) findViewById(R.id.nameTextView)).setText(todo3.get(0).getTodo(indexOfTodo).getName());
        ((TextView) findViewById(R.id.deadlineTextView)).setText(String.valueOf(todo3.get(0).getTodo(indexOfTodo).getDeadline()));
        ((TextView) findViewById(R.id.noteTextView)).setText(String.valueOf(todo3.get(0).getTodo(indexOfTodo).getNote()));


    }
}