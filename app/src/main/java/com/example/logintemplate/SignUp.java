package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername, textInputEditTextEmail, textInputEditTextPassword;
    Button signUpButton;
    Button signUpBackButton;

    ProgressBar signUpProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextUsername = findViewById(R.id.signup_username);
        textInputEditTextEmail = findViewById(R.id.signup_email);
        textInputEditTextPassword = findViewById(R.id.signup_password);
        signUpButton = findViewById(R.id.sign_up_button);
        signUpProgressBar = findViewById(R.id.signup_progressbar);
        signUpBackButton = findViewById(R.id.sign_up_back_button);
        System.out.println(textInputEditTextEmail.getText());
        System.out.println(signUpButton);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email, password;
                username = String.valueOf(textInputEditTextUsername.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if (!username.equals("") && !email.equals("") && !password.equals("")) {
                    signUpProgressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "username";
                            field[1] = "email";
                            field[2] = "password";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = username;
                            data[1] = email;
                            data[2] = password;
                            PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/SignUp.php", "POST", field, data, "string");
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    signUpProgressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if (result == "Sign Up Success") {
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
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUpBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoggedOutCenter.class);
                startActivity(intent);
            }
        });



    }
}