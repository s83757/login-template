package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FindUsers extends AppCompatActivity {
    static String preferredLanguage;
    String preferredTimeZone;


    public static void initPreferences(String selectedLanguage, String selectedTimeZone) {
        preferredLanguage = "aj";

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_other_users2);
    }
}