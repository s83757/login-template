package com.example.logintemplate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity {
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
        Intent intent = new Intent(this, RickRoll.class); // insert activity here
        startActivity(intent);
    }
}