package com.example.emory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Type;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddTodoListActivity extends AppCompatActivity {
    TodoList todolist = TodoList.getInstance();
    ArrayList<Todo> todos = new ArrayList<>();
    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        FloatingActionButton floatBtn = findViewById(R.id.addTodoBtn);
        floatBtn.setOnClickListener(view -> getDetails());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
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
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData();
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_checked,
                todolist.getAllTodo())
        );

        lv.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            Intent nextActivity = new Intent(AddTodoListActivity.this, TodoListDetails.class);
            nextActivity.putExtra("indexOfTodo", i);
            startActivity(nextActivity);
        });

    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        todolist.remove(position);
        adapter.notifyDataSetChanged();
    }*/

    /*@Override
    protected void onPause() {
        super.onPause();
        Log.d("tag", "app onPause...");
        saveData();
    }*/

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String dataReceived = sharedPreferences.getString("SAMPLE", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Todo>>() {
        }.getType();
        todos = gson.fromJson(dataReceived, type);
        Log.d("hello", String.valueOf(todos));
    }

    public void getDetails() {
        Intent intent = new Intent(this, TodoDetailsActivity.class);
        startActivityForResult(intent, 1);
    }

    /*public void saveData() {
        Gson gson = new Gson();
        SharedPrefsSingleton sp = SharedPrefsSingleton.getInstance();
        sp.put("SAMPLE", gson.toJson(todos));
    }*/

}

