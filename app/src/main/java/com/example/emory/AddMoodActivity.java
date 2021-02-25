package com.example.emory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


public class AddMoodActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView chosenDate;
    private DayMonthYear fullDate;
    private ImageButton btnSmile, btnHappy, btnLove, btnSad, btnCry, btnAngry, btnClose;
    private Button btnSaveMood;
    public static final String EMORY_SHARED_PREFERENCES = "EMORY_SHARED_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        chosenDate = findViewById(R.id.calendar);
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
        chosenDate.setText(fullDate.getCurrentFullDate());

        btnSmile.setOnClickListener((View v) -> {
            startNote(R.drawable.smile);
        });

        btnHappy.setOnClickListener((View v) -> {
            startNote(R.drawable.happy);
        });

        btnLove.setOnClickListener((View v) -> {
            startNote(R.drawable.love);
        });

        btnSad.setOnClickListener((View v) -> {
            startNote(R.drawable.sad);
        });

        btnCry.setOnClickListener((View v) -> {
            startNote(R.drawable.cry);
        });

        btnAngry.setOnClickListener((View v) -> {
            startNote(R.drawable.angry);
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

    public void startNote(int drawable) {
        btnSaveMood.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, WriteNoteActivity.class);
        intent.putExtra("icon", drawable);
        startActivity(intent);
    }

    public void onCalendarClick(View v) {
        fullDate.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        chosenDate.setText(fullDate.setFullDate(year, month, dayOfMonth));
    }
}