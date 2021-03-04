package com.example.emory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddMoodActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView chosenDate;
    private DayMonthYear fullDate;
    private Button btnSaveMood;
    public static final String EMORY_SHARED_PREFERENCES = "EMORY_SHARED_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        chosenDate = findViewById(R.id.calendar);
        ImageButton btnGood = findViewById(R.id.good);
        ImageButton btnHappy = findViewById(R.id.happy);
        ImageButton btnExcited = findViewById(R.id.excited);
        ImageButton btnSad = findViewById(R.id.sad);
        ImageButton btnAwful = findViewById(R.id.awful);
        ImageButton btnTerrible = findViewById(R.id.terrible);
        ImageButton btnClose = findViewById(R.id.close);
        btnSaveMood = findViewById(R.id.saveMood);
        btnSaveMood.setVisibility(View.GONE);

        fullDate = new DayMonthYear();
        chosenDate.setText(fullDate.getCurrentFullDate());

        btnGood.setOnClickListener((View v) -> {
            startNote("good");
        });

        btnHappy.setOnClickListener((View v) -> {
            startNote("happy");
        });

        btnExcited.setOnClickListener((View v) -> {
            startNote("excited");
        });

        btnSad.setOnClickListener((View v) -> {
            startNote("sad");
        });

        btnAwful.setOnClickListener((View v) -> {
            startNote("awful");
        });

        btnTerrible.setOnClickListener((View v) -> {
            startNote("terrible");
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

    public void startNote(String drawable) {
        btnSaveMood.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, WriteNoteActivity.class);
        intent.putExtra("icon", drawable);
        intent.putExtra("date", chosenDate.getText().toString());
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