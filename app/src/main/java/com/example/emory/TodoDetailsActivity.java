package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class TodoDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TodoList todolist = TodoList.getInstance();
    ArrayList<TodoList> todos = new ArrayList<>();
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
        /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.toDoList);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.entries:
                    startActivity(new Intent(this, EntriesActivity.class));
                    return true;

                case R.id.addMood:
                    startActivity(new Intent(this, AddMoodActivity.class));
                    return true;

                case R.id.toDoList:
                    return true;

                case R.id.settings:
                    startActivity(new Intent(this, SettingsActivity.class));
                    return true;

                case R.id.moodGraph:
                    startActivity(new Intent(this, MoodAnalytics.class));
                    return true;
            }
            return false;
        });*/
        saveTodoList();
    }

    public void saveTodoList() {
        Button addBtn = findViewById(R.id.addTodo);
        addBtn.setOnClickListener(v -> {
            nameEditText = findViewById(R.id.nameTodoEditText);
            deadlineEditText = findViewById(R.id.deadlineEditText);
            noteEditText = findViewById(R.id.noteEditText);
            Todo todo = new Todo(nameEditText.getText().toString(),
                    deadlineEditText.getText().toString(), noteEditText.getText().toString());
            todolist.addActivity(todo);
            todos.add(todolist);
            Gson gson = new Gson();
            SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("SAMPLE", gson.toJson(todos));
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