package com.example.MentorMarketplace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class UserPageLoader extends Fragment {

    private View view;
    private ImageView loaded_profile_pic;
    private TextView loaded_profile_name;
    private TextView loaded_profile_languages;
    private TextView loaded_profile_mentor_language;
    private TextView loaded_profile_time_zone;
    private TextView loaded_profile_rating;
    private TextView loaded_profile_bio;
    private TextView loaded_profile_socials;

    private int current_user = 0;


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_page_loader, container, false);

        ArrayList<String[]> user_info = ((InteractWithUsers) getActivity()).getUser_info_array();

        current_user = ((InteractWithUsers) getActivity()).getCurrent_user();

        loaded_profile_pic = view.findViewById(R.id.loaded_profile_pic);
        loaded_profile_name = view.findViewById(R.id.loaded_profile_name);
        loaded_profile_languages = view.findViewById(R.id.loaded_profile_primary_language);
        loaded_profile_mentor_language = view.findViewById(R.id.loaded_profile_mentor_language);
        loaded_profile_time_zone = view.findViewById(R.id.loaded_profile_time_zone);
        loaded_profile_rating = view.findViewById(R.id.loaded_profile_rating);
        loaded_profile_bio = view.findViewById(R.id.loaded_profile_bio);
        loaded_profile_socials = view.findViewById(R.id.loaded_profile_socials);
        // loaded_profile_teach_language = view.findViewById(R.id.@#$#$$$%@^)

        String[] current_user_info = user_info.get(current_user);

        System.out.println("Woah!");
        for (String p : current_user_info) {
            System.out.println(p);
        }
        loaded_profile_name.setText("Name: " + current_user_info[2]);
        if (current_user_info[4].charAt(0) == '-') {
            loaded_profile_time_zone.setText("Time zone: UTC-" + current_user_info[4]);
        }
        else{
            loaded_profile_time_zone.setText("Time zone: UTC+" + current_user_info[4]);
        }

        loaded_profile_languages.setText("Languages: " + current_user_info[6]);
        loaded_profile_mentor_language.setText("Mentor Language: " + current_user_info[16]);
        loaded_profile_rating.setText("Rating: " + current_user_info[14]);
        loaded_profile_bio.setText("Bio: " + current_user_info[13]);
        loaded_profile_socials.setText("Socials: " + current_user_info[5]);

        return view;
    }

    /*
    TextInputLayout SelectLanguage;
    AutoCompleteTextView autoCompleteLanguage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_down_layout_xml);
        SelectLanguage = (TextInputLayout) getView().findViewById(R.id.SelectLanguageFilter);
        autoCompleteLanguage = (AutoCompleteTextView) getView().findViewById(R.id.autoCompleteTextViewLanguage);
    }*/

}