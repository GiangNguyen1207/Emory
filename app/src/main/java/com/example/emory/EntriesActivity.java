package com.example.emory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EntriesActivity extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;
    private ArrayList<DiaryList> diaryList = new ArrayList<>();
    private static final String SHARED_PREFS = "sharedPrefs";

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

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.entries:
                    return true;

                case R.id.addMood:
                    Intent intent = new Intent(EntriesActivity.this, AddMoodActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        });

        createListView();
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

    public void createListView() {
        Gson gson = new Gson();
        ArrayList<Diary> diaries;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String date = month.getText().toString();
        Integer daysOfMonths = monthYear.getDaysInMonth(date);

        for (int i = 1; i <= daysOfMonths; i++) {
            String data = sharedPreferences.getString(i + ". " + date, null);
            Type diaryType = new TypeToken<ArrayList<Diary>>() {
            }.getType();
            diaries = gson.fromJson(data, diaryType);
            diaryList.add(new DiaryList(monthYear.getCurrentFullDate(), diaries));
        }

        Log.d("diaryList", String.valueOf(diaryList));

        DiaryAdapter diaryAdapter = new DiaryAdapter(this, diaryList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(diaryAdapter);
    }
}