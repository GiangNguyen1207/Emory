package com.example.emory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoodAnalyticsActivity extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;
    private MoodGraph moodGraph = new MoodGraph();
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
        createDataPoints();
        showGraph();
    }

    public void onNext(View v) {
        String text = month.getText().toString();
        String nextMonthYear = monthYear.getNextMonthYear(text);
        month.setText(nextMonthYear);
        createDataPoints();
        showGraph();
    }

    public void createDataPoints() {
        String mthYear = month.getText().toString();
        Integer currentDay = monthYear.getCurrentDay();
        moodGraph = new MoodGraph(currentDay + 1);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        ArrayList<Diary> diaries;
        for (int i = 1; i <= currentDay; i++) {
            Gson gson = new Gson();
            String data = sharedPreferences.getString(i + ". " + mthYear, String.valueOf(new ArrayList<Diary>()));
            Type diaryType = new TypeToken<ArrayList<Diary>>() {
            }.getType();
            diaries = gson.fromJson(data, diaryType);
            double averageMood = moodGraph.getMoodAverage(diaries);
            moodGraph.addToDataPoints(i, averageMood);

        }
        moodGraph.addToSeries();
    }

    public void showGraph() {
        GraphView graph = findViewById(R.id.graph);
        graph.removeAllSeries();

        int currentDay = monthYear.getCurrentDay();
        int daysInMonth = monthYear.getDaysInMonth(month.getText().toString());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(1.0);
        graph.getViewport().setMaxX(moodGraph.getMaxX(currentDay, daysInMonth));
        graph.getViewport().setMaxY(5.0);

        createDataPoints();
        graph.addSeries(moodGraph.getSeries());
    }
}