package com.example.logintemplate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class FindOtherUsers extends Fragment {

    private View view;


    @Override
    public void onResume() {
        super.onResume();

        String[] languages = getResources().getStringArray(R.array.Languages);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.drop_down_layout_xml,
                languages);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);

        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find_other_users, container, false);

        return view;
    }
}