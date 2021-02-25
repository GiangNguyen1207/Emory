package com.example.emory;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

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
        chosenDate.setText(fullDate.getFullDate());

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

        SharedPreferences sharedPreferences = getSharedPreferences(EMORY_SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        Diary diary = new Diary();
        editor.putString(fullDate.getCurrentMonthYear(), gson.toJson(diary));

        Intent intent = new Intent(this, Activities.class);
        intent.putExtra("icon", drawable);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        Log.d("Date", currentDate);
        chosenDate.setText(currentDate);
    }

    public void onCalendarClick(View v) {
        DialogFragment datePicker = new CalendarPicker();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }
}