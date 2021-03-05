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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.emory.SharedPref.TODOLIST;

public class TodoDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ArrayList<Todo> todo2 = new ArrayList<>();
    private DayMonthYear fullDate;
    EditText nameEditText, noteEditText;
    TextView deadlineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        SharedPref.init(getApplicationContext());
        fullDate = new DayMonthYear();
        deadlineEditText = findViewById(R.id.deadlineEditText);
        deadlineEditText.setText(fullDate.getCurrentFullDate());
        saveTodoList();
    }

    public void saveTodoList() {
        String dataReceived = SharedPref.read(TODOLIST, String.valueOf(new ArrayList<Todo>()));
        Gson gson1 = new Gson();
        Type type = new TypeToken<ArrayList<Todo>>() {
        }.getType();
        todo2 = gson1.fromJson(dataReceived, type);
        Button addBtn = findViewById(R.id.addTodo);
        addBtn.setOnClickListener(v -> {
            nameEditText = findViewById(R.id.nameTodoEditText);
            deadlineEditText = findViewById(R.id.deadlineEditText);
            noteEditText = findViewById(R.id.noteEditText);
            if (nameEditText.getText().toString().matches("")) {
                Toast.makeText(this, "You did not enter Name", Toast.LENGTH_SHORT).show();
            } else {
                Todo todo = new Todo(nameEditText.getText().toString(),
                        deadlineEditText.getText().toString(), noteEditText.getText().toString());
                todo2.add(todo);
                Log.d("haha", String.valueOf(todo2));
                Gson gson2 = new Gson();
                SharedPref.write(TODOLIST, gson2.toJson(todo2));
                Intent intent = new Intent(TodoDetailsActivity.this, AddTodoListActivity.class);
                startActivity(intent);
                finish();
            }
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

