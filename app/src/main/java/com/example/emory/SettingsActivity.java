package com.example.emory;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.entries:
                    startActivity(new Intent(this, EntriesActivity.class));
                    return true;

                case R.id.addMood:
                    startActivity(new Intent(this, AddMoodActivity.class));
                    return true;

                case R.id.toDoList:
                    startActivity(new Intent(this, AddTodoListActivity.class));
                    return true;

                case R.id.settings:
                    return true;

                case R.id.moodGraph:
                    startActivity(new Intent(this, MoodAnalyticsActivity.class));
                    return true;
            }
            return false;
        });


        Button alarmBtn = findViewById(R.id.setAlarm);
        alarmBtn.setOnClickListener((View v) -> {
            Intent intent = new Intent(SettingsActivity.this, ReminderActivity.class);
            startActivity(intent);
        });

    }

}
