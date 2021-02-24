package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddMoodActivity extends AppCompatActivity {
    private TextView calendar;
    private DayMonthYear fullDate;
    private ImageButton btnSmile, btnHappy, btnLove, btnSad, btnCry, btnAngry;
    private Button btnSaveMood;
    private String selectedMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        calendar = findViewById(R.id.calendar);
        btnSmile = findViewById(R.id.smile);
        btnHappy = findViewById(R.id.happy);
        btnLove = findViewById(R.id.love);
        btnSad = findViewById(R.id.sad);
        btnCry = findViewById(R.id.cry);
        btnAngry = findViewById(R.id.angry);
        btnSaveMood = findViewById(R.id.saveMood);
        btnSaveMood.setVisibility(View.GONE);

        fullDate = new DayMonthYear();
        calendar.setText(fullDate.getFullDate());

        selectedMood = "";
        btnSmile.setOnClickListener((View v) -> {
            selectedMood = "smile";
            startNote(btnSmile);
        });

        btnHappy.setOnClickListener((View v) -> {
            selectedMood = "happy";
            startNote(btnHappy);
        });

        btnLove.setOnClickListener((View v) -> {
            selectedMood = "love";
            startNote(btnLove);
        });

        btnSad.setOnClickListener((View v) -> {
            selectedMood = "sad";
            startNote(btnSad);
        });

        btnCry.setOnClickListener((View v) -> {
            selectedMood = "cry";
            startNote(btnCry);
        });

        btnAngry.setOnClickListener((View v) -> {
            selectedMood = "angry";
            startNote(btnAngry);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //chosenBtn.setBackgroundResource(R.color.secondary);
    }

    public void startNote(ImageButton chosenBtn) {
        btnSaveMood.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, Activities.class);
        startActivity(intent);
    }
}