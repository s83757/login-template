package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class profile_editor_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        Button cancelButton = (Button) findViewById(R.id.cancel_profile_edit);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //startActivity(new Intent(TitleScreen.this, LoginActivity.class))
            public void onClick(View view) {
                cancel();
            }
        });
    }
    public void cancel() {
        //Intent intent = new Intent(this, );
    }

    //@Override
    public void updateProfile(View view) {
        int tr = 4;
    }
}