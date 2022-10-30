package com.example.MentorMarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        goLoginButton.setOnClickListener(view -> goLogin());

    }
    private void configureSignUpButton() {
        Button goSignUpButton = findViewById(R.id.goSignUpButton);
        goSignUpButton.setOnClickListener(view -> goSignUp());

    }
    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity2.class);
        startActivity(intent);
    }
    public void goSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}