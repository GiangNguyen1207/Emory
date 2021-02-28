package com.example.emory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class AddImage extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int GALLERY_REQUEST = 1;
    Uri mCapturedImageURI;
    ImageButton btn1, btn2;
    private String selectedImagePath;

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
        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
        finish();
    }

    public void openGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        cameraIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(cameraIntent, GALLERY_REQUEST);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    String selectedImageUri = data.getData().getPath();
                    System.out.println("Image Path : " + selectedImagePath);
                    ImageView imageView = findViewById(R.id.photoChosen);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));

                }
                break;
            case GALLERY_REQUEST:
            if (resultCode == RESULT_OK) {
                String image = data.getData().getPath();
                Log.d("bitmap", String.valueOf(image));
                //Uri selectedImageUri = data.getData();
                //selectedImagePath = getPath(selectedImageUri);
                //System.out.println("Image Path : " + selectedImagePath);
            /*try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                Log.d("bitmap", String.valueOf(bitmap));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
                break;
            }
        }
    }
}

