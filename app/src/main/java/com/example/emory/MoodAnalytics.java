package com.example.emory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoodAnalytics extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;
    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_analytics);

        month = findViewById(R.id.month);
        setDate();
        showGraph();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.moodGraph);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.entries:
                    startActivity(new Intent(this, EntriesActivity.class));
                    return true;

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

    public void setDate() {
        TextView month = findViewById(R.id.month);
        monthYear = new DayMonthYear();
        month.setText(monthYear.getCurrentMonthYear());
    }

    public void onBack(View v) {
        String text = month.getText().toString();
        String prevMonthYear = monthYear.getPrevMonthYear(text);
        month.setText(prevMonthYear);
    }

    public void onNext(View v) {
        String text = month.getText().toString();
        String nextMonthYear = monthYear.getNextMonthYear(text);
        month.setText(nextMonthYear);
    }

    public void getDiaries() {
        String mthYear = month.getText().toString();
        Integer daysOfMonths = monthYear.getDaysInMonth(mthYear);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        ArrayList<Diary> diaries;
        for (int i = 1; i <= daysOfMonths; i++) {
            Gson gson = new Gson();
            String data = sharedPreferences.getString(i + ". " + mthYear, String.valueOf(new ArrayList<Diary>()));
            Type diaryType = new TypeToken<ArrayList<Diary>>() {
            }.getType();
            diaries = gson.fromJson(data, diaryType);
            getMoodAverage(diaries);
        }
    }

    public void getMoodAverage(ArrayList<Diary> diaries) {
        int sum = 0;
        double average;
        for (Diary diary : diaries) {
            String moodName = getResources().getResourceEntryName(diary.getMood());
            sum += diary.getMoodIndicator(moodName);
            if (diaries.indexOf(diary) == diaries.size() - 1) {
                average = sum/diaries.size();
                Log.d("average", String.valueOf(average));
            } else continue;
        }
    }

    public void showGraph() {
        GraphView graph = findViewById(R.id.graph);
        int max_x = monthYear.getDaysInMonth(month.getText().toString());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(max_x);

        getDiaries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0),
                new DataPoint(20, 0),
                new DataPoint(21, 0),
                new DataPoint(22, 0),
                new DataPoint(23, 0),
                new DataPoint(24, 0),
                new DataPoint(25, 0),
                new DataPoint(26, 0),
                new DataPoint(27, 0),
                new DataPoint(28, 0),
                new DataPoint(29, 0),
                new DataPoint(30, 0),
                new DataPoint(31, 0)
        });
        graph.addSeries(series);
    }
}