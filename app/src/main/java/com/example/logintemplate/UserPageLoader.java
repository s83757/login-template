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
    public void getData() {
        String inputEmail = mEmailView.getText().toString().trim();
        String inputPassword = mPasswordView.getText().toString().trim();

        if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(LoginActivity2.this, "Empty Field",
                    Toast.LENGTH_SHORT).show();
        } else {

            mProgressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = inputEmail;
                    data[1] = inputPassword;
                    PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/Login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            mProgressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result == "Login Success") {
                                System.out.println(result);
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity2.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                System.out.println(result);
                            }
                            //End ProgressBar (Set visibility to GONE)
                            //Log.i("PutData", result);
                        }
                    }
                    //End Write and Read data with URL
                }
            });

        }

}