package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        //replace info in fragment2
        getData("1", "5", "0", "English");
        replaceFragment(new UserPageLoader());

    }

    public void getData(String self_id, String rating, String timezone, String primary_language) {
        //Starting Write and Read data with URL
        //Creating array for parameters
        String[] field = new String[4];
        field[0] = "self_id";
        field[1] = "rating";
        field[2] = "time_zone";
        field[3] = "primary_language";
        //Creating array for data
        String[] data = new String[4];
        data[0] = self_id;
        data[1] = rating;
        data[2] = timezone;
        data[3] = primary_language;
        PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/GetMatchingUsers.php", "POST", field, data, "string");
        if (putData.startPut()) {
            if (putData.onComplete()) {
                //mProgressBar.setVisibility(View.GONE);
                String result = putData.getResult();
                if (result.length() == 1) {
                    System.out.println(result);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    System.out.println(result);
                }
                //End ProgressBar (Set visibility to GONE)
                //Log.i("PutData", result);
            }
        }
        //End Write and Read data with URL
    }

}