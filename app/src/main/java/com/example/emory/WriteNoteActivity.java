package com.example.emory;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
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
    private String note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        receiveEmotion();
        getActivity();
        getImage();
        receiveImage();
        saveData();
    }

    public void saveData() {
        ImageButton doneIcon = findViewById(R.id.doneIcon);
        doneIcon.setOnClickListener((View v) -> {
            getNote();
            diary = new Diary(icon, activities, note);
            saveDiary();
        });
    }

    public void receiveEmotion() {
        Intent intent = getIntent();
        icon = intent.getIntExtra("icon", 0);
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
                Log.d("clicked", String.valueOf(R.id.familyIcon));
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
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString(DATE_KEY, gson.toJson(diary));
        editor.apply();
    }


    public void getImage() {
        ImageButton addImage = findViewById(R.id.addPhoto);
        addImage.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, AddImage.class);
            startActivity(intent);
        });
    }

    public void receiveImage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImage = preferences.getString("image", null);
        ImageView photo = findViewById(R.id.photoChosen);
        Bitmap bitmap = BitmapFactory.decodeFile(mImage);
        photo.setImageBitmap(bitmap);
    }
}