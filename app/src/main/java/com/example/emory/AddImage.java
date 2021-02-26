package com.example.emory;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class AddImage extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int GALLERY_REQUEST = 1;
    Uri mCapturedImageURI;
    private BitmapFactory.Options options;
    private byte[] fileName;
    ImageButton btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image_fragment);
        btn1 = findViewById(R.id.takePhotoBtn);
        btn2 = findViewById(R.id.openGalleryBtn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.takePhotoBtn) {
            openCamera();
        } else if (v.getId() == R.id.openGalleryBtn) {
            openGallery();
        }
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = managedQuery(mCapturedImageURI, projection, null, null, null);
                    int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String capturedImageFilePath = cursor.getString(column_index_data);
                    Log.d("photos*******", " in camera take int  " + capturedImageFilePath);

                    Bitmap photo_camera = BitmapFactory.decodeFile(capturedImageFilePath, options);

                    if (data != null) {
                        editor.putString("image", capturedImageFilePath);
                    }
                }
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

//                  Bitmap photo = BitmapFactory.decodeFile(filePath);
                    Bitmap photo_gallery = BitmapFactory.decodeFile(filePath, options);
                    editor.putString("image", filePath);
                }

        }
        editor.commit();
    }
}

