package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class profile_editor_activity extends AppCompatActivity {

    private ArrayList<String> user_info_array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        getData("1");

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

    public void getData(String self_id) {
        //Starting Write and Read data with URL
        //Creating array for parameters
        String[] field = new String[1];
        field[0] = "self_id";

        //Creating array for data
        String[] data = new String[1];
        data[0] = self_id;

        PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/GetDataOfUserById.php", "POST", field, data, "string");
        if (putData.startPut()) {
            if (putData.onComplete()) {
                //mProgressBar.setVisibility(View.GONE);
                String result = putData.getResult();

                result = result.substring(1, result.length() - 1);  // Outside brackets are removed

                System.out.println(result);

                String[] split = result.split(",");

                for (int j = 0; j < split.length; j++) {

                    if (String.valueOf(split[j].charAt(0)).equals("\"") &&
                            String.valueOf(split[j].
                                            charAt(split[j].length() - 1)).
                                    equals("\"")) {

                        user_info_array.add(split[j].substring(1, split[j].length() - 1));
                        System.out.println(user_info_array.get(j));
                    }


                }


                //End ProgressBar (Set visibility to GONE)
                //Log.i("PutData", result);

            }
        }

        //End Write and Read data with URL
    }
}