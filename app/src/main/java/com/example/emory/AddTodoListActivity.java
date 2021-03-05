package com.example.emory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class AddTodoListActivity extends AppCompatActivity {
    ArrayList<TodoListSingleton> todo2 = new ArrayList<>();
    private static final String SHARED_PREFS = "sharedPrefs";
    ArrayAdapter<Todo> arrayAdapter;
    TodoListSingleton todolist = TodoListSingleton.getInstance();
    private int position = 0;

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
        ListView lv = findViewById(R.id.listView);
        registerForContextMenu(lv);
        reload();
    }

    private void getDetails() {
        Intent intent = new Intent(this, TodoDetailsActivity.class);
        startActivityForResult(intent, 1);
    }

    private void reload() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String dataReceived = sharedPreferences.getString("todolist", String.valueOf(new ArrayList<TodoListSingleton>()));
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TodoListSingleton>>() {
        }.getType();
        todo2 = gson.fromJson(dataReceived, type);
        Log.d("hello", String.valueOf(todo2));

        ListView lv = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                todo2.get(0).getAllTodo());
        lv.setAdapter(arrayAdapter);


        lv.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {

        });

        lv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            position = i;
            openContextMenu(lv);
            return true;
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.todolist_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                Intent nextActivity = new Intent(AddTodoListActivity.this, TodoListDetails.class);
                nextActivity.putExtra("indexOfTodo", position);
                Log.d("mi", String.valueOf(position));
                startActivity(nextActivity);
                return true;
            case R.id.option_2:
                removeItem(todo2.get(0).getTodo(position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    public void removeItem(Todo todo) {
        arrayAdapter.remove(todo);
        todolist.remove(todo);
        saveData();
        arrayAdapter.notifyDataSetChanged();
    }

    private void saveData() {
        Gson gson = new Gson();
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        todo2.add(todolist);
        editor.putString("todolist", gson.toJson(todo2));
        editor.apply();
        Log.d("hi", String.valueOf(todolist));
    }


}

