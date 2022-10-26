package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class InteractWithUsers extends AppCompatActivity {

    private Button SearchButton, SavedButton, user_prev_button, user_next_button;

    private int current_user = 0, total_users = 0;  // The user page that you are currently viewing
    private String language_filter = "English";
    private String timezone_filter = "0";
    private ArrayList<String[]> user_info_array = new ArrayList<>();

    public int getCurrent_user() {
        return current_user;
    }

    public ArrayList<String[]> getUser_info_array() {
        return user_info_array;
    }

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
        SearchButton = findViewById(R.id.filter_button_id);
        SavedButton = findViewById(R.id.search_users_id);

        user_prev_button = findViewById(R.id.prev_button_id);
        user_next_button = findViewById(R.id.next_button_id);

        user_prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_user = (current_user + total_users - 1) % total_users;
                replaceFragment(new UserPageLoader());
            }
        });

        user_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_user = (current_user + 1) % total_users;
                replaceFragment(new UserPageLoader());
            }
        });

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
        getData("1", "5", timezone_filter, language_filter);
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

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                System.out.println(result);

                result = result.substring(1, result.length() - 1);
                String split_1[] = result.split("]");

                for (int i = 0; i < split_1.length; i++) {
                    if (i > 0) split_1[i] = split_1[i].substring(2);
                    else split_1[i] = split_1[i].substring(1);

                    user_info_array.add(split_1[i].split(","));
                    for (int j = 0; j < user_info_array.get(i).length; j++) {
                        // shit.get(i)[j] = shit.get(i)[j].strip();

                        if (String.valueOf(user_info_array.get(i)[j].charAt(0)).equals("\"") &&
                                String.valueOf(user_info_array.get(i)[j].
                                        charAt(user_info_array.get(i)[j].length() - 1)).
                                        equals("\"")) {
                            //System.out.println(shit.get(i)[j]);
                            user_info_array.get(i)[j] = user_info_array.get(i)[j].
                                    substring(1, user_info_array.get(i)[j].length() - 1);
                        }
                        System.out.println(user_info_array.get(i)[j]);
                    }
                }

                total_users = user_info_array.size();

                //End ProgressBar (Set visibility to GONE)
                //Log.i("PutData", result);
            }
        }
        //End Write and Read data with URL
    }
}