package com.example.logintemplate;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class FilterUsers extends Fragment {
    public void establishDropdown(String[] targetArray, AutoCompleteTextView targetAutoCompleteView) {
        //String[] targetArray = getResources().getStringArray(targetArrayId);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(requireContext(), R.layout.drop_down_layout_xml,
                targetArray);

        //AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewLanguage);

        targetAutoCompleteView.setAdapter(arrayAdapter);
    }

    private View view;
    private Button begin_search_button;
    private AutoCompleteTextView dropdown_languages;
    private AutoCompleteTextView dropdown_timezone;

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

        autoCompleteTextView.setAdapter(arrayAdapter);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter_users, container, false);
        begin_search_button = view.findViewById(R.id.begin_search_button);
        dropdown_languages = view.findViewById(R.id.autoCompleteTextViewLanguage);
        dropdown_timezone = view.findViewById(R.id.autoCompleteTextViewTimeZone);

        begin_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getActivity(), FindUsers.class);
                // startActivity(intent);

                String language_filter_string = dropdown_languages.getText().toString();

                if (!language_filter_string.equals("Select A Language")) {
                    ((InteractWithUsers) getActivity()).setLanguage_filter(
                            language_filter_string
                    );
                }

                String timezone = dropdown_timezone.getText().toString();

                if (!timezone.equals("Select A Time Zone")) {
                    timezone = timezone.substring(3);
                    if (timezone.charAt(0) == '+') {
                        timezone.substring(1);
                    }
                    ((InteractWithUsers) getActivity()).setTimezone_filter(
                            timezone
                    );
                }


                ((InteractWithUsers) getActivity()).searchUsers();
                //getActivity().getSupportFragmentManager().popBackStack();
            }

        });



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