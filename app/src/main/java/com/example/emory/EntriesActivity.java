package com.example.emory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EntriesActivity extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        month = findViewById(R.id.month);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.entries);

        monthYear = new DayMonthYear();
        String currentMonthYear = monthYear.getCurrentMonthYear();

        month.setText(currentMonthYear);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addMood:
                        Intent intent = new Intent(getApplicationContext(), AddMoodActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

    public void onBack(View v) {
        month = findViewById(R.id.month);
        String text = month.getText().toString();
        String[] dateValues = text.split(", ");

        String prevMonthYear = monthYear.getPrevMonthYear(dateValues[0], Integer.parseInt(dateValues[1]));
        month.setText(prevMonthYear);
    }

    public void onNext(View v) {
        month = findViewById(R.id.month);
        String text = month.getText().toString();
        String[] dateValues = text.split(", ");

        String nextMonthYear = monthYear.getNextMonthYear(dateValues[0], Integer.parseInt(dateValues[1]));
        month.setText(nextMonthYear);
    }
}