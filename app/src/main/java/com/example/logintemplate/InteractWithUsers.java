package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InteractWithUsers extends AppCompatActivity {

    Button SearchButton, SavedButton;

    private String language_filter = "English";
    private String timezone_filter = "UTC-8";

    public String getLanguage_filter() {
        return language_filter;
    }

    public void setLanguage_filter(String language_filter) {
        this.language_filter = language_filter;
    }

    public String getTimezone_filter() {
        return timezone_filter;
    }

    public void setTimezone_filter(String timezone_filter) {
        this.timezone_filter = timezone_filter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact_with_users);
        SearchButton = findViewById(R.id.fragment1btn);
        SavedButton = findViewById(R.id.fragment2btn);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new FilterUsers());

            }
        });

        SavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new UserPageLoader());

            }
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.UserInteractFrameLayout,fragment);
        fragmentTransaction.commit();

    }

    public void searchUsers() {
        replaceFragment(new UserPageLoader());
    }

}