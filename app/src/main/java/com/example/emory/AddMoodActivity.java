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
    private ImageButton btnSmile, btnHappy, btnLove, btnSad, btnCry, btnAngry, btnClose;
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
        btnClose = findViewById(R.id.close);
        btnSaveMood = findViewById(R.id.saveMood);
        btnSaveMood.setVisibility(View.GONE);

        fullDate = new DayMonthYear();
        calendar.setText(fullDate.getFullDate());

        selectedMood = "";
        btnSmile.setOnClickListener((View v) -> {
            startNote(R.drawable.smile, btnSmile);
        });

        btnHappy.setOnClickListener((View v) -> {
            startNote(R.drawable.happy, btnHappy);
        });

        btnLove.setOnClickListener((View v) -> {

            startNote(R.drawable.love, btnLove);
        });

        btnSad.setOnClickListener((View v) -> {
            startNote(R.drawable.sad, btnSad);
        });

        btnCry.setOnClickListener((View v) -> {
            startNote(R.drawable.cry, btnCry);
        });

        btnAngry.setOnClickListener((View v) -> {
            startNote(R.drawable.angry, btnAngry);
        });

        btnClose.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, EntriesActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //chosenBtn.setBackgroundResource(R.color.secondary);
    }

    public void startNote(int drawable, ImageButton chosenBtn) {
        btnSaveMood.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, WriteNoteActivity.class);
        intent.putExtra("icon", drawable);
        startActivity(intent);
    }
}