package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class profile_editor_activity extends AppCompatActivity {

    private final ArrayList<String> user_info_array = new ArrayList<>();

    public void establishDropdown(String[] targetArray, AutoCompleteTextView targetAutoCompleteView) {
        //String[] targetArray = getResources().getStringArray(targetArrayId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.drop_down_layout_xml,
                targetArray);

        //AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewLanguage);

        targetAutoCompleteView.setAdapter(arrayAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        establishDropdown(getResources().getStringArray(R.array.Languages), findViewById(R.id.language_auto_complete));
        establishDropdown(getResources().getStringArray(R.array.Time_Zones), findViewById(R.id.time_zone_auto_complete));
    }
    private int signum(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        } else {
            return 0;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        getData("1");

        Button cancelButton = (Button) findViewById(R.id.cancel_profile_edit);
        Button updateButton = (Button) findViewById(R.id.update_profile_button);

        AutoCompleteTextView dropdown_languages = findViewById(R.id.language_auto_complete);
        AutoCompleteTextView dropdown_timezones = findViewById(R.id.time_zone_auto_complete);

        EditText name_editor = findViewById(R.id.editTextName);
        EditText email_editor = findViewById(R.id.editTextEmail);

        /* Load set values into the profile editor */

        // Name
        if (!user_info_array.get(2).equals("null")) {
            name_editor.setText(user_info_array.get(2));
        }

        // Email
        if (!user_info_array.get(5).equals("null")) {
            email_editor.setText(user_info_array.get(5));
        }

        // Timezone
        if (!user_info_array.get(4).equals("null")) {
            if (user_info_array.get(4).charAt(0) == '-') {
                dropdown_timezones.setText("UTC" + user_info_array.get(4));
            }
            else{
                dropdown_timezones.setText("UTC" + "+" + user_info_array.get(4));
            }
        }

        // Language
        if (!user_info_array.get(6).equals("null")) {
            dropdown_languages.setText(user_info_array.get(6));
        }


        cancelButton.setOnClickListener(view -> leave());
        updateButton.setOnClickListener(view -> {
            // Update changes. do SQL stuff
            update();
        });
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

                result = result.substring(2, result.length() - 2);  // Outside brackets are removed

                System.out.println(result);

                String[] split = result.split(",");

                for (int j = 0; j < split.length; j++) {

                    String addition = split[j];
                    if (String.valueOf(split[j].charAt(0)).equals("\"") &&
                            String.valueOf(split[j].
                                            charAt(split[j].length() - 1)).
                                    equals("\"")) {

                        addition = split[j].substring(1, split[j].length() - 1);
                    }

                    user_info_array.add(addition);
                    System.out.println(user_info_array.get(j));


                }


                //End ProgressBar (Set visibility to GONE)
                //Log.i("PutData", result);

            }
        }

        //End Write and Read data with URL
    }

    public void update() {
        String[] field = new String[11];
        field[0] = "self_id";
        field[1] = "username";
        field[2] = "person_name";
        field[3] = "time_zone";
        field[4] = "email";
        field[5] = "primary_language";
        field[6] = "DOB";
        field[7] = "city";
        field[8] = "country";
        field[9] = "phone";
        field[10] = "Pfp";

        //Creating array for data
        String[] data = new String[11];
        data[0] = user_info_array.get(0);
        data[1] = user_info_array.get(1);
        data[2] = user_info_array.get(2);
        data[3] = user_info_array.get(4);
        data[4] = user_info_array.get(5);
        data[5] = user_info_array.get(6);
        data[6] = user_info_array.get(7);
        data[7] = user_info_array.get(8);
        data[8] = user_info_array.get(9);
        data[9] = user_info_array.get(10);
        data[10] = user_info_array.get(12);

        PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/SendProfileUpdate.php", "POST", field, data, "string");
        if (putData.startPut()) {
            if (putData.onComplete()) {
                //mProgressBar.setVisibility(View.GONE);
                String result = putData.getResult();

                if (result.equals("Profile Update Success")) {
                    System.out.println(result);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    leave();
                } else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    System.out.println(result);
                }


                //End ProgressBar (Set visibility to GONE)
                //Log.i("PutData", result);

            }
        }
    }
}