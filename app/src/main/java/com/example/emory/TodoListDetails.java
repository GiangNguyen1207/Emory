package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class TodoListDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_details);
        TodoList todoList = TodoList.getInstance();
        Bundle b = getIntent().getExtras();
        int indexOfTodo = b.getInt("indexOfTodo");

        ((TextView) findViewById(R.id.nameTextView)).setText(todoList.getTodo(indexOfTodo).getName());
        ((TextView) findViewById(R.id.deadlineTextView)).setText(String.valueOf(todoList.getTodo(indexOfTodo).getDeadline()));
        ((TextView) findViewById(R.id.noteTextView)).setText(String.valueOf(todoList.getTodo(indexOfTodo).getNote()));


    }
}