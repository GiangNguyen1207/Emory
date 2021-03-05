package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.emory.SharedPref.TODOLIST;

public class TodoListDetails extends AppCompatActivity {
    ArrayList<Todo> todo3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_details);
        SharedPref.init(getApplicationContext());
        String dataReceived = SharedPref.read(TODOLIST, String.valueOf(new ArrayList<Todo>()));
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Todo>>() {
        }.getType();
        todo3 = gson.fromJson(dataReceived, type);
        Bundle b = getIntent().getExtras();
        int indexOfTodo = b.getInt("indexOfTodo");

        ((TextView) findViewById(R.id.nameTextView)).setText(todo3.get(indexOfTodo).getName());
        ((TextView) findViewById(R.id.deadlineTextView)).setText(String.valueOf(todo3.get(indexOfTodo).getDeadline()));
        ((TextView) findViewById(R.id.noteTextView)).setText(String.valueOf(todo3.get(indexOfTodo).getNote()));


    }
}