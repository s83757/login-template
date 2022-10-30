package com.example.MentorMarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity {

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        configureEnterButton();
    }

    private void configureEnterButton() {
        Button enterButton = (Button) findViewById(R.id.enter_app_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //startActivity(new Intent(TitleScreen.this, LoginActivity.class))
            public void onClick(View view) {
                enterApp();
            }
        });

    }
    public void enterApp() {
        Intent intent = new Intent(this, LoggedOutCenter.class); // insert activity here
        startActivity(intent);
    }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
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