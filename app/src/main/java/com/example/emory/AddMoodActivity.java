package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AddMoodActivity extends AppCompatActivity {
    private TextView calendar;
    private DayMonthYear fullDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        calendar = findViewById(R.id.calendar);

        fullDate = new DayMonthYear();
        calendar.setText(fullDate.getFullDate());
    }
}