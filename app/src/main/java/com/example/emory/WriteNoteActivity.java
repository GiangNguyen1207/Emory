package com.example.emory;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private Dialog dialog;

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

    //loop to check id of imageButton
    public void getActivity() {
        ViewGroup layout = findViewById(R.id.linearLayout3);
        for(int h=0; h < layout.getChildCount(); h++) {
            View view = layout.getChildAt(h);
            view.setOnClickListener(this);

        }
        /*ImageButton familyButton = findViewById(R.id.familyIcon);
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
        birthdayButton.setOnClickListener(this);*/
    }

    /*
    create a layout object
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.familyIcon:
                iconClicked(v.getId(), "family", R.drawable.action_ic_family);
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
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_import_picture);
            checkBtnClick(dialog);
            dialog.show();
        });
    }

    public void checkBtnClick(Dialog dialog) {
        ImageButton btnCamera = dialog.findViewById(R.id.takePhotoBtn);
        ImageButton btnGallery = dialog.findViewById(R.id.openGalleryBtn);
        btnCamera.setOnClickListener((View v) -> {
            openCamera();
        });
        btnGallery.setOnClickListener((View v) -> {
            openGallery();
        });
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    public void openGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        cameraIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(cameraIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
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
                        Toast.makeText(this, "Error loading file", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    bitmap = (Bitmap) extras.get("data");
                    encodeBitmap();
                    ImageView imageView = findViewById(R.id.photoChosen);
                    imageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(this, "Error loading file", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
        dialog.dismiss();
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

    private void iconClicked(int id, String name, int drawable) {
        /*check if the activities arr list contains the actions family or not
                if not, the first time user click, add + change background
                if yes, the second time change background + remove
                 */
        Action action = new Action(name, drawable);
        ImageButton imgBtn = findViewById(id);
        if(activities.contains(action)){
            activities.remove(action);
            imgBtn.setBackgroundColor(getColor(R.color.secondary));
        } else {
            activities.add(action);
            imgBtn.setBackgroundColor(getColor(R.color.white));
        }
    }
}