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

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoodAnalyticsActivity extends AppCompatActivity {
    private TextView month;
    private DayMonthYear monthYear;
    private BottomNavigationView bottomNavigationView;
    private MoodGraph moodGraph = new MoodGraph();
    private MoodCounter terrible, awful, sad, good, happy, excited;
    private static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_analytics);

        setCounter();
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
        month = findViewById(R.id.month);
        monthYear = new DayMonthYear();
        month.setText(monthYear.getCurrentMonthYear());
    }

    public void onBack(View v) {
        String text = month.getText().toString();
        String prevMonthYear = monthYear.getPrevMonthYear(text);
        month.setText(prevMonthYear);
        setCounter();
        findTotalDays();
        showGraph();
    }

    public void onNext(View v) {
        String text = month.getText().toString();
        String nextMonthYear = monthYear.getNextMonthYear(text);
        month.setText(nextMonthYear);
        setCounter();
        findTotalDays();
        showGraph();
    }

    public boolean compareMonthYear() {
        String mthYear = month.getText().toString();
        DayMonthYear curMthYear = new DayMonthYear();

        return curMthYear.getCurrentMonthYear().equals(mthYear);
    }

    public void findTotalDays() {
        String mthYear = month.getText().toString();
        Integer currentDay = monthYear.getCurrentDay();
        Integer daysInMonth = monthYear.getDaysInMonth(mthYear);

        if (compareMonthYear()) {
            createDataPoints(currentDay, mthYear);
        } else {
            createDataPoints(daysInMonth, mthYear);
        }
    }

    public void createDataPoints(int size, String mthYear) {
        ArrayList<Diary> diaries;
        moodGraph = new MoodGraph(size + 1);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        for (int i = 1; i <= size; i++) {
            Gson gson = new Gson();
            String data = sharedPreferences.getString(i + ". " + mthYear, String.valueOf(new ArrayList<Diary>()));
            Type diaryType = new TypeToken<ArrayList<Diary>>() {
            }.getType();
            diaries = gson.fromJson(data, diaryType);
            countMood(diaries);
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

        if (compareMonthYear()) {
            graph.getViewport().setMaxX(moodGraph.getMaxX(currentDay, daysInMonth));
        } else {
            graph.getViewport().setMaxX(moodGraph.getMaxX(daysInMonth, daysInMonth));
        }

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(1.0);
        graph.getViewport().setMaxY(5.0);

        findTotalDays();
        graph.addSeries(moodGraph.getSeries());
    }

    public void setCounter() {
        terrible = new MoodCounter();
        awful = new MoodCounter();
        sad = new MoodCounter();
        good = new MoodCounter();
        happy = new MoodCounter();
        excited = new MoodCounter();
    }

    public void countMood(ArrayList<Diary> diaries) {
        for (Diary diary : diaries) {
            switch (diary.getMood()) {
                case "terrible":
                    terrible.addValue();
                    break;
                case "awful":
                    awful.addValue();
                    break;
                case "sad":
                    sad.addValue();
                    break;
                case "good":
                    good.addValue();
                    break;
                case "happy":
                    happy.addValue();
                    break;
                case "excited":
                    excited.addValue();
                    break;
            }
        }
        setMood();
    }

    public void setMood() {
        TextView terribleVal = findViewById(R.id.terribleCount);
        TextView awfulVal = findViewById(R.id.awfulCount);
        TextView sadVal = findViewById(R.id.sadCount);
        TextView goodVal = findViewById(R.id.goodCount);
        TextView happyVal = findViewById(R.id.happyCount);
        TextView excitedVal = findViewById(R.id.excitedCount);

        terribleVal.setText(String.valueOf(terrible.getCount()));
        awfulVal.setText(String.valueOf(awful.getCount()));
        sadVal.setText(String.valueOf(sad.getCount()));
        goodVal.setText(String.valueOf(good.getCount()));
        happyVal.setText(String.valueOf(happy.getCount()));
        excitedVal.setText(String.valueOf(excited.getCount()));

        /*Log.d("trb", String.valueOf(terrible.getCount()));
        Log.d("awful", String.valueOf(awful.getCount()));*/
        //Log.d("sad", String.valueOf(sad.getCount()));
        //Log.d("good", String.valueOf(good.getCount()));
        Log.d("happy", String.valueOf(happy.getCount()));
        //Log.d("excited", String.valueOf(excited.getCount()));//

    }
}