package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MoodAnalytics extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_analytics);
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