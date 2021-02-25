package com.example.emory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WriteNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String DATE_KEY = "Today";
    private Diary diary;
    private ArrayList<Activities> activities = new ArrayList<>();
    private int icon;
    String note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        receiveEmotion();

        saveData();
    }

    public void saveData() {

        ImageButton doneIcon = findViewById(R.id.doneIcon);
        doneIcon.setOnClickListener((View v) -> {
            getActivity();
            getNote();
            diary = new Diary(icon, activities, note);
            saveDiary();
        });
    }

    public void receiveEmotion() {
        Intent intent = getIntent();
        icon = intent.getIntExtra("icon", 0);
        Drawable drawable = getResources().getDrawable(icon);
        Log.d("icon", String.valueOf(drawable));
        ImageView imageView = findViewById(R.id.iconChosen);
        imageView.setImageDrawable(drawable);
    }

    public void getActivity() {

        ImageButton familyButton = findViewById(R.id.familyIcon);
        ImageButton friendButton = findViewById(R.id.friendIcon);
        ImageButton loveButton = findViewById(R.id.loveIcon);
        ImageButton sportButton = findViewById(R.id.sportIcon);
        ImageButton exerciseButton = findViewById(R.id.exerciseIcon);
        ImageButton moviesButton = findViewById(R.id.movieIcon);
        ImageButton sleepButton = findViewById(R.id.sleepIcon);
        ImageButton travelButton = findViewById(R.id.travelIcon);
        ImageButton studyButton = findViewById(R.id.studyIcon);
        ImageButton cleanButton = findViewById(R.id.cleanIcon);
        ImageButton workButton = findViewById(R.id.workIcon);
        ImageButton shoppingButton = findViewById(R.id.shoppingIcon);
        ImageButton gameButton = findViewById(R.id.gameIcon);
        ImageButton birthdayButton = findViewById(R.id.birthdayIcon);

        familyButton.setOnClickListener(this);
        friendButton.setOnClickListener(this);
        loveButton.setOnClickListener(this);
        sportButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);
        moviesButton.setOnClickListener(this);
        sleepButton.setOnClickListener(this);
        travelButton.setOnClickListener(this);
        studyButton.setOnClickListener(this);
        cleanButton.setOnClickListener(this);
        workButton.setOnClickListener(this);
        shoppingButton.setOnClickListener(this);
        gameButton.setOnClickListener(this);
        birthdayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.familyIcon:
                activities.add(new Activities("family", R.id.familyIcon));
            case R.id.friendIcon:
                activities.add(new Activities("friend", R.id.friendIcon));
            case R.id.loveIcon:
                activities.add(new Activities("love", R.id.loveIcon));
            case R.id.sportIcon:
                activities.add(new Activities("sport", R.id.sportIcon));
            case R.id.exerciseIcon:
                activities.add(new Activities("exercise", R.id.exerciseIcon));
            case R.id.movieIcon:
                activities.add(new Activities("movie", R.id.movieIcon));
            case R.id.sleepIcon:
                activities.add(new Activities("sleep", R.id.sleepIcon));
            case R.id.travelIcon:
                activities.add(new Activities("travel", R.id.travelIcon));
            case R.id.studyIcon:
                activities.add(new Activities("study", R.id.studyIcon));
            case R.id.cleanIcon:
                activities.add(new Activities("clean", R.id.cleanIcon));
            case R.id.workIcon:
                activities.add(new Activities("work", R.id.workIcon));
            case R.id.shoppingIcon:
                activities.add(new Activities("shopping", R.id.shoppingIcon));
            case R.id.gameIcon:
                activities.add(new Activities("game", R.id.gameIcon));
            case R.id.birthdayIcon:
                activities.add(new Activities("birthday", R.id.birthdayIcon));
        }
    }

    public void getNote() {
        EditText editText = findViewById(R.id.writeNote);
        note = editText.getText().toString();
        Log.d("note", note);
    }

    public void saveDiary() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString(DATE_KEY, gson.toJson(diary));
        editor.apply();
    }
}
