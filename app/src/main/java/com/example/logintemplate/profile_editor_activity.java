package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;

public class profile_editor_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        Button cancelButton = (Button) findViewById(R.id.cancel_profile_edit);
        Button updateButton = (Button) findViewById(R.id.update_profile_button);

        cancelButton.setOnClickListener(view -> leave());
        updateButton.setOnClickListener(view -> {
            // Update changes. do SQL stuff
            leave();
        });
    }

    public void establishDropdown(String[] targetArray, AutoCompleteTextView targetAutoCompleteView) {
        //String[] targetArray = getResources().getStringArray(targetArrayId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.drop_down_layout_xml,
                targetArray);

        //AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewLanguage);

        targetAutoCompleteView.setAdapter(arrayAdapter);
    }

    public void leave() {
        startActivity(new Intent(getApplicationContext(), LoggedInCenter.class));
        finish();
    }

    //@Override
    public void updateProfile(View view) {
        int tr = 4;
    }
}