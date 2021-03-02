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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String email,password;
    EditText edEmail,edPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);
//        getSupportActionBar().hide();
        btnSignUp=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);
        edEmail=findViewById(R.id.edLoginEmail);
        edPassword=findViewById(R.id.edLoginPassword);
        sharedPreferences = getSharedPreferences("SignUp",MODE_PRIVATE);
        final Boolean isUserLogin = sharedPreferences.getBoolean("isUserLogin",false);
        email=sharedPreferences.getString("Email","DEFAULT_EMAIL");
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
                String email_in = edEmail.getText().toString();
                String password_in = edPassword.getText().toString();
                if(TextUtils.isEmpty(email_in)){
                    edEmail.setError(getResources().getString(R.string.error_message));
                }else if (TextUtils.isEmpty(password_in)){
                    edPassword.setError(getResources().getString(R.string.error_message));
                }else {
                    login(email_in,password_in);
                }
            }
        });
    }
    private void login(String email_in, String password_in) {
        if(email_in.equals(email) && password_in.equals(password)) {
            sharedPreferences.edit().putBoolean("isUserLogin",false).apply();
            // startMainActivity();
            Toast.makeText(SignInActivity.this,"Login SuccessFul",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SignInActivity.this,"Username or password is incorrect",Toast.LENGTH_LONG).show();
        }
    }
}