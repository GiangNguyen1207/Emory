package com.example.emory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText edName,edAge,edEmail,edPassword;
    Button btnSignUp;
    String name,age,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edName=findViewById(R.id.edEmail);
        edAge=findViewById(R.id.edAge);
        edEmail=findViewById(R.id.edEmail);
        edPassword=findViewById(R.id.edPassword);
        btnSignUp=findViewById(R.id.btnSignUp);
        sharedPreferences = getApplicationContext().getSharedPreferences("SignUp", 0);
        editor = sharedPreferences.edit();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=edName.getText().toString();
                age=edAge.getText().toString();
                email=edEmail.getText().toString();
                password=edPassword.getText().toString();
                if(TextUtils.isEmpty(name)){
                    edName.setError(getResources().getString(R.string.error_message));
                }else if(TextUtils.isEmpty(age)){
                    edAge.setError(getResources().getString(R.string.error_message));
                }else if (TextUtils.isEmpty(email)){
                    edEmail.setError(getResources().getString(R.string.error_message));
                }else if (TextUtils.isEmpty(password)){
                    edPassword.setError(getResources().getString(R.string.error_message));
                }
                else {
                    editor.putString("Name", name);
                    editor.putString("Age",age);
                    editor.putString("Email",email);
                    editor.putString("Password",password);
                    editor.putBoolean("isUserLogin",true);
                    editor.commit();
                    Toast.makeText(SignupActivity.this,"User has been register successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignupActivity.this,SignInActivity.class);
                    startActivity(intent);

                }
            }
        });

    }


}