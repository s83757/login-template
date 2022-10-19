package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedOutCenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out_center);
        configureLoginButton();
        configureSignUpButton();
    }

    private void configureLoginButton() {
        Button goLoginButton = findViewById(R.id.goLoginButton);
        goLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //startActivity(new Intent(TitleScreen.this, LoginActivity.class))
            public void onClick(View view) {
                goLogin();
            }
        });

    }
    private void configureSignUpButton() {
        Button goSignUpButton = findViewById(R.id.goSignUpButton);
        goSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //startActivity(new Intent(TitleScreen.this, LoginActivity.class))
            public void onClick(View view) {
                goSignUp();
            }
        });

    }
    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity2.class); // insert activity here
        startActivity(intent);
    }
    public void goSignUp() {
        Intent intent = new Intent(this, SignUp.class); // insert activity here
        startActivity(intent);
    }
}