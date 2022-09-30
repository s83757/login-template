package com.example.logintemplate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;


public class FindOtherUsers  extends Fragment {
    public void establishDropdown(String[] targetArray, AutoCompleteTextView targetAutoCompleteView) {
        //String[] targetArray = getResources().getStringArray(targetArrayId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(requireContext(), R.layout.drop_down_layout_xml,
                targetArray);

        //AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewLanguage);

        targetAutoCompleteView.setAdapter(arrayAdapter);
    }

    private View view;


    @Override
    public void onResume() {
        super.onResume();
        establishDropdown(getResources().getStringArray(R.array.Languages), view.findViewById(R.id.autoCompleteTextViewLanguage));
        establishDropdown(getResources().getStringArray(R.array.Time_Zones), view.findViewById(R.id.autoCompleteTextViewTimeZone));
/*
        String[] languages = getResources().getStringArray(R.array.Languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(requireContext(), R.layout.drop_down_layout_xml,
                languages);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewLanguage);

        autoCompleteTextView.setAdapter(arrayAdapter);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find_other_users, container, false);

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