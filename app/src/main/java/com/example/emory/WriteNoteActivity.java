package com.example.emory;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.renderscript.ScriptGroup;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WriteNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final int GALLERY_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private ArrayList<Action> activities = new ArrayList<>();
    private String icon;
    private String date, note;
    private ArrayList<Diary> diaries = new ArrayList<>();
    private String encodePic;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        getDataFromAddMood();
        getActivity();
        getImage();
        saveData();

        /*SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences settings = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        settings.edit().remove("2. March, 2021").commit();*/
    }

    public void getDataFromAddMood() {
        Intent intent = getIntent();
        icon = intent.getStringExtra("icon");
        date = intent.getStringExtra("date");

        int resourceId = getResources().getIdentifier("com.example.emory:drawable/" + icon, null, null);
        ImageView imageView = findViewById(R.id.iconChosen);
        Drawable drawable = getResources().getDrawable(resourceId);
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
        ImageButton birthdayButton = findViewById(R.id.celebration);

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
                activities.add(new Action("family", R.drawable.action_ic_family));
                break;
            case R.id.friendIcon:
                activities.add(new Action("friends", R.drawable.action_ic_friends));
                break;
            case R.id.loveIcon:
                activities.add(new Action("favourite", R.drawable.action_ic_favourite));
                break;
            case R.id.sportIcon:
                activities.add(new Action("fitness", R.drawable.action_ic_fitness));
                break;
            case R.id.exerciseIcon:
                activities.add(new Action("walking", R.drawable.action_ic_walking));
                break;
            case R.id.movieIcon:
                activities.add(new Action("movie", R.drawable.action_ic_movie));
                break;
            case R.id.sleepIcon:
                activities.add(new Action("sleep", R.drawable.action_ic_sleep));
                break;
            case R.id.travelIcon:
                activities.add(new Action("travel", R.drawable.action_ic_travel));
                break;
            case R.id.studyIcon:
                activities.add(new Action("study", R.drawable.action_ic_study));
                break;
            case R.id.cleanIcon:
                activities.add(new Action("cleaning", R.drawable.action_ic_cleaning));
                break;
            case R.id.workIcon:
                activities.add(new Action("work", R.drawable.action_ic_work));
                break;
            case R.id.shoppingIcon:
                activities.add(new Action("shopping", R.drawable.action_ic_shopping));
                break;
            case R.id.gameIcon:
                activities.add(new Action("games", R.drawable.action_ic_games));
                break;
            case R.id.celebration:
                activities.add(new Action("celebration", R.drawable.action_ic_celebration));
                break;
        }
    }

    public void getNote() {
        EditText editText = findViewById(R.id.writeNote);
        note = editText.getText().toString();
    }

    public void getImage() {
        ImageButton addImage = findViewById(R.id.addPhoto);
        addImage.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, AddImage.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()));
                encodeBitmap();
                //Bitmap bMapScaled = Bitmap.createScaledBitmap(bitmap, 150, 100, true);
                /*Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);*/
                ImageView imageView = findViewById(R.id.photoChosen);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /*if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.photoChosen);
            imageView.setImageBitmap(image);

        }*/
    }

    public void encodeBitmap() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        byte[] b = output.toByteArray();
        encodePic = Base64.encodeToString(b, Base64.DEFAULT);
    }


    public void saveDiary() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String data = sharedPreferences.getString(date, null);

        if (data != null) {
            Type diaryType = new TypeToken<ArrayList<Diary>>() {
            }.getType();
            diaries = gson.fromJson(data, diaryType);
        }

        diaries.add(new Diary(icon, activities, note, encodePic));
        editor.putString(date, gson.toJson(diaries));
        editor.apply();
    }

    public void saveData() {
        FloatingActionButton floatBtn = findViewById(R.id.doneIcon);
        floatBtn.setOnClickListener(view -> {
            getNote();
            saveDiary();
            Intent intent = new Intent(this, EntriesActivity.class);
            startActivity(intent);
        });
    }
}