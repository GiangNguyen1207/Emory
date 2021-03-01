package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoodAnalytics extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_analytics);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.moodGraph);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.entries:
                    startActivity(new Intent(this, EntriesActivity.class));

                case R.id.addMood:
                    startActivity(new Intent(this, AddMoodActivity.class));
                    return true;

                case R.id.toDoList:
                    startActivity(new Intent(this, TodoDetailsActivity.class));
                    return true;

                case R.id.settings:
                    startActivity(new Intent(this, SettingsActivity.class));
                    return true;

                case R.id.moodGraph:
                    return true;
            }
            return false;
        });
    }

    public void onBack(View v) {
        month = findViewById(R.id.month);
        String text = month.getText().toString();
        String prevMonthYear = monthYear.getPrevMonthYear(text);
        month.setText(prevMonthYear);
    }

    public void onNext(View v) {
        month = findViewById(R.id.month);
        String text = month.getText().toString();
        String nextMonthYear = monthYear.getNextMonthYear(text);
        month.setText(nextMonthYear);
    }
}