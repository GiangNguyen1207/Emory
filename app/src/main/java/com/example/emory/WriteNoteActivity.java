package com.example.emory;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WriteNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int GALLERY_REQUEST = 1;
    private static final String SHARED_PREFS = "sharedPrefs";
    private String selectedImagePath;
    private ArrayList<Activities> activities = new ArrayList<>();
    private int icon;
    private String date, note;
    private ArrayList<Diary> diaries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        getDataFromAddMood();
        getActivity();
        getImage();
        saveData();
    }

    public void getDataFromAddMood() {
        Intent intent = getIntent();
        icon = intent.getIntExtra("icon", 0);
        date = intent.getStringExtra("date");

        Drawable drawable = getResources().getDrawable(icon);
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
                break;
            case R.id.friendIcon:
                activities.add(new Activities("friend", R.id.friendIcon));
                break;
            case R.id.loveIcon:
                activities.add(new Activities("love", R.id.loveIcon));
                break;
            case R.id.sportIcon:
                activities.add(new Activities("sport", R.id.sportIcon));
                break;
            case R.id.exerciseIcon:
                activities.add(new Activities("exercise", R.id.exerciseIcon));
                break;
            case R.id.movieIcon:
                activities.add(new Activities("movie", R.id.movieIcon));
                break;
            case R.id.sleepIcon:
                activities.add(new Activities("sleep", R.id.sleepIcon));
                break;
            case R.id.travelIcon:
                activities.add(new Activities("travel", R.id.travelIcon));
                break;
            case R.id.studyIcon:
                activities.add(new Activities("study", R.id.studyIcon));
                break;
            case R.id.cleanIcon:
                activities.add(new Activities("clean", R.id.cleanIcon));
                break;
            case R.id.workIcon:
                activities.add(new Activities("work", R.id.workIcon));
                break;
            case R.id.shoppingIcon:
                activities.add(new Activities("shopping", R.id.shoppingIcon));
                break;
            case R.id.gameIcon:
                activities.add(new Activities("game", R.id.gameIcon));
                break;
            case R.id.birthdayIcon:
                activities.add(new Activities("birthday", R.id.birthdayIcon));
                break;
        }
    }

    public void getNote() {
        EditText editText = findViewById(R.id.writeNote);
        note = editText.getText().toString();
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
        diaries.add(new Diary(icon, activities, note));
        editor.putString(date, gson.toJson(diaries));
        editor.apply();
    }

    public void saveData() {
        ImageButton doneIcon = findViewById(R.id.doneIcon);
        doneIcon.setOnClickListener((View v) -> {
            getNote();
            saveDiary();
        });
    }

    public void getImage() {
        ImageButton addImage = findViewById(R.id.addPhoto);
        addImage.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, AddImage.class);
            startActivityForResult(intent, 1);
        });
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    selectedImagePath = getPath(selectedImageUri);
                    System.out.println("Image Path : " + selectedImagePath);
                    ImageView imageView = findViewById(R.id.photoChosen);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));

                }
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    selectedImagePath = getPath(selectedImageUri);
                    System.out.println("Image Path : " + selectedImagePath);
                    ImageView imageView = findViewById(R.id.photoChosen);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
                }

        }
    }


    @Override
    protected void onPause() {
        SharedPreferences sp = getSharedPreferences("AppSharedPref", MODE_PRIVATE); // Open SharedPreferences with name AppSharedPref
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ImagePath", selectedImagePath); // Store selectedImagePath with key "ImagePath". This key will be then used to retrieve data.
        editor.commit();
        super.onPause();
    }

    protected void onResume() {
        SharedPreferences sp = getSharedPreferences("AppSharedPref", MODE_PRIVATE);
        selectedImagePath = sp.getString("ImagePath", "");
        Bitmap myBitmap = BitmapFactory.decodeFile(selectedImagePath);
        ImageView im1 = findViewById(R.id.photoChosen);
        im1.setImageBitmap(myBitmap);
        super.onResume();
    }
}