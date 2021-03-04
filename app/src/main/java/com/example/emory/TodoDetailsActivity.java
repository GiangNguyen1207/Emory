package com.example.emory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TodoDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TodoListSingleton todolist = TodoListSingleton.getInstance();
    ArrayList<TodoListSingleton> todos = new ArrayList<>();
    ArrayList<TodoListSingleton> todo2 = new ArrayList<>();
    private static final String SHARED_PREFS = "sharedPrefs";
    private DayMonthYear fullDate;
    EditText nameEditText, noteEditText;
    TextView deadlineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        fullDate = new DayMonthYear();
        deadlineEditText = findViewById(R.id.deadlineEditText);
        deadlineEditText.setText(fullDate.getCurrentFullDate());

        saveTodoList();
    }

    public void saveTodoList() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String dataReceived = sharedPreferences.getString("todolist", String.valueOf(new ArrayList<TodoListSingleton>()));
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<TodoListSingleton>>() {
        }.getType();
        todo2 = gson1.fromJson(dataReceived, type);
        Button addBtn = findViewById(R.id.addTodo);
        addBtn.setOnClickListener(v -> {
            nameEditText = findViewById(R.id.nameTodoEditText);
            deadlineEditText = findViewById(R.id.deadlineEditText);
            noteEditText = findViewById(R.id.noteEditText);
            Todo todo = new Todo(nameEditText.getText().toString(),
                    deadlineEditText.getText().toString(), noteEditText.getText().toString());
            todo2.get(0).addActivity(todo);
            todos.add(todo2.get(0));
            Log.d("haha", String.valueOf(todos));
            Gson gson2 = new Gson();
            SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("todolist", gson2.toJson(todos));
            editor.apply();
            Intent intent = new Intent(TodoDetailsActivity.this, AddTodoListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void onCalendarClick(View v) {
        fullDate.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        deadlineEditText.setText(fullDate.setFullDate(year, month, dayOfMonth));
    }
}

