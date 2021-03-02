package com.example.emory;

import android.app.DatePickerDialog;
import android.content.Intent;
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
    private ImageButton btnSmile, btnHappy, btnExcited, btnSad, btnCry, btnAwful, btnClose;
    private Button btnSaveMood;
    public static final String EMORY_SHARED_PREFERENCES = "EMORY_SHARED_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        chosenDate = findViewById(R.id.calendar);
        btnSmile = findViewById(R.id.smile);
        btnHappy = findViewById(R.id.happy);
        btnExcited = findViewById(R.id.excited);
        btnSad = findViewById(R.id.sad);
        btnCry = findViewById(R.id.cry);
        btnAwful = findViewById(R.id.awful);
        btnClose = findViewById(R.id.close);
        btnSaveMood = findViewById(R.id.saveMood);
        btnSaveMood.setVisibility(View.GONE);

        fullDate = new DayMonthYear();
        chosenDate.setText(fullDate.getCurrentFullDate());

        btnSmile.setOnClickListener((View v) -> {
            startNote("smile");
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

        btnCry.setOnClickListener((View v) -> {
            startNote("cry");
        });

        btnAwful.setOnClickListener((View v) -> {
            startNote("awful");
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