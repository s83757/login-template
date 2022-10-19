package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logintemplate.ui.login.LoginActivity;

public class RickRoll extends AppCompatActivity {
    int ie = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rick_roll);
        configure5Button();
    }
    private void configure5Button() {
        Button enterButton = (Button) findViewById(R.id.exit_rickroll);
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
}