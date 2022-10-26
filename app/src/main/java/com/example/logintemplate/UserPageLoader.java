package com.example.logintemplate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class UserPageLoader extends Fragment {

    private View view;
    private ImageView loaded_profile_pic;
    private TextView loaded_profile_name;
    private TextView loaded_profile_languages;
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
        loaded_profile_languages = view.findViewById(R.id.loaded_profile_languages);
        loaded_profile_time_zone = view.findViewById(R.id.loaded_profile_time_zone);
        loaded_profile_rating = view.findViewById(R.id.loaded_profile_rating);
        loaded_profile_bio = view.findViewById(R.id.loaded_profile_bio);
        loaded_profile_socials = view.findViewById(R.id.loaded_profile_socials);

        String[] current_user_info = user_info.get(current_user);

        loaded_profile_name.setText(current_user_info[2]);
        loaded_profile_time_zone.setText(current_user_info[4]);
        loaded_profile_languages.setText(current_user_info[6]);
        loaded_profile_rating.setText(current_user_info[13]);
        loaded_profile_bio.setText(" To Be Implemented ");
        loaded_profile_socials.setText(current_user_info[5]);

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