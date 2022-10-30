package com.example.MentorMarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    private EditText mEmailView, mPasswordView;

    //private DBHelper mDBHelper;
    private ProgressBar mProgressBar;

    //private TextView mLoginRegisterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // mDBHelper = new DBHelper(this);

        Button mBackButton = findViewById(R.id.login_go_back);
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        Button mSignInButton = findViewById(R.id.login_button);
        mProgressBar = findViewById(R.id.login_progressbar);

        mSignInButton.setOnClickListener(v -> {

            String inputEmail = mEmailView.getText().toString().trim();
            String inputPassword = mPasswordView.getText().toString().trim();

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(LoginActivity2.this, "Empty Field",
                        Toast.LENGTH_SHORT).show();
            } else {

                mProgressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL

                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";

                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = inputEmail;
                    data[1] = inputPassword;

                    PutData putData = new PutData("http://ec2-44-202-164-77.compute-1.amazonaws.com/Login.php", "POST", field, data, "string");

                    if (putData.startPut()) {
                        if (putData.onComplete()) {

                            mProgressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            System.out.println(result);

                            //System.out.println("YYYYYY");

                            if (result.equals("Login Success")) {

                                //System.out.print("XXXXXXX");

                                System.out.println(result);

                                Toast.makeText(getApplicationContext(),
                                        result, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), LoggedInCenter.class);
                                startActivity(intent);

                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        result, Toast.LENGTH_SHORT).show();
                                System.out.println(result);
                            }
                            // End ProgressBar (Set visibility to GONE)
                            // Log.i("PutData", result);
                        }
                    }
                    // End Write and Read data with URL
                });
            }
        });


        TextView mRegister = findViewById(R.id.login_register_text);
        mRegister.setOnClickListener(v -> {
            Intent intent = new Intent (LoginActivity2.this, RegisterActivity.class);
            startActivity(intent);
        });

        mBackButton.setOnClickListener(v -> {
            Intent intent = new Intent (LoginActivity2.this, TitleScreen.class);
            startActivity(intent);
        });
    }
    public void clearText() {
        mEmailView.setText("");
        mPasswordView.setText("");
    }
}