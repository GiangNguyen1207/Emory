package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TodoDetailsActivity extends AppCompatActivity {
    //TodoList todolist = TodoList.getInstance();
    ArrayList<Todo> todos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        saveTodoList();
    }

    public void saveTodoList() {
        Button addBtn = findViewById(R.id.addTodo);
        addBtn.setOnClickListener(v -> {
            EditText nameEditText = findViewById(R.id.nameTodoEditText);
            EditText deadlineEditText = findViewById(R.id.deadlineEditText);
            EditText noteEditText = findViewById(R.id.noteEditText);

            Todo todo = new Todo(nameEditText.getText().toString(),
                    deadlineEditText.getText().toString(), noteEditText.getText().toString());
            todos.add(todo);
            Gson gson = new Gson();
            SharedPrefsSingleton sp = SharedPrefsSingleton.getInstance();
            sp.put("SAMPLE", gson.toJson(todos));
            finish();
        });
    }
}