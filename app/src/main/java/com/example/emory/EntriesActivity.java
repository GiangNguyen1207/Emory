package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class EntriesActivity extends AppCompatActivity {
    private TextView month;
    private MonthYear monthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        month = findViewById(R.id.month);

        monthYear = new MonthYear();
        String currentMonthYear = monthYear.getCurrentMonthYear();

        month.setText(currentMonthYear);
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