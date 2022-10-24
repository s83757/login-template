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
import android.widget.Toast;


public class UserPageLoader extends Fragment {

    private View view;


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_page_loader, container, false);

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