package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedInCenter extends AppCompatActivity {
    private Button goToInteractWithUsersButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_center);

        goToInteractWithUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToInteract();
            }
        });
    }
    private void goToInteract() {
        Intent intent = new Intent(this, InteractWithUsers.class);
        startActivity(intent);
    }
}