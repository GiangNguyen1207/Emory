package com.example.emory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    Button btnSignUp,btnLogin;
    //initializing sharedpreference
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String email,password;
    EditText edEmail,edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make activity to full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
        btnSignUp=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);
        edEmail=findViewById(R.id.edLoginEmail);
        edPassword=findViewById(R.id.edLoginPassword);
        //shared get data from sharedPreferences
        sharedPreferences = getSharedPreferences("SignUp",MODE_PRIVATE);
        //get saved email from sharedPreferences
        email=sharedPreferences.getString("Email","DEFAULT_EMAIL");
        //get saved password  from sharedPreferences
        password= sharedPreferences.getString("Password","DEFAULT_PASSWORD");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailIn = edEmail.getText().toString();
                String passwordIn = edPassword.getText().toString();
                //check empty field
                if(TextUtils.isEmpty(emailIn)){
                    edEmail.setError(getResources().getString(R.string.error_message));
                }else if (TextUtils.isEmpty(passwordIn)){
                    edPassword.setError(getResources().getString(R.string.error_message));
                }else {
                    login(emailIn,passwordIn);
                }
            }
        });
    }
    private void login(String emailIn, String passwordIn) {
        if(emailIn.equals(email) && passwordIn.equals(password)) {
            sharedPreferences.edit().putBoolean("isUserLogin",false).apply();
            // startMainActivity();
            Toast.makeText(SignInActivity.this,"Login SuccessFul",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SignInActivity.this,MainScreen.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(SignInActivity.this,"Username or password is incorrect",Toast.LENGTH_LONG).show();
        }
    }
}