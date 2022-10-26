package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoggedInCenter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_center);

        Button goToInteractWithUsersButton = findViewById(R.id.goToInteractWithUsersButton);
        Button edit_profile_button = findViewById(R.id.edit_profile_button);

        goToInteractWithUsersButton.setOnClickListener(view -> goToInteract());
        edit_profile_button.setOnClickListener(view -> goToEditProfile());

    }

    private void goToInteract() {
        Intent intent = new Intent(this, InteractWithUsers.class);
        startActivity(intent);
    }

    private void goToEditProfile() {
        Intent intent = new Intent(this, profile_editor_activity.class);
        startActivity(intent);
    }
}