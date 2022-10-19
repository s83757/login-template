package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private Button mSignInButton;
    private TextView mRegister;
    private DBHelper mDBHelper;
    private ProgressBar mProgressBar;
    private TextView mLoginRegisterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mDBHelper = new DBHelper(this);
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        mSignInButton = findViewById(R.id.login_button);
        mProgressBar = findViewById(R.id.login_progressbar);
        /*mLoginRegisterText = findViewById(R.id.login_register_text);
        mLoginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });*/

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                    //all fields are complete
                    // if email is an actual email, and password and email match.

                    /*
                    SQLiteDatabase db = mDBHelper.getReadableDatabase();

                    Cursor cursor = db.rawQuery("SELECT * FROM " +
                                    DBContract.FeedEntry.TABLE_NAME + " WHERE" +
                                    DBContract.FeedEntry.EMAIL + "='" + inputEmail+ "'" + "AND" +
                                    DBContract.FeedEntry.PASSWORD + "='" + inputPassword + "'",
                            null);

                    if (cursor.moveToFirst()) {
                        String username = cursor.getString(cursor.getColumnIndexOrThrow(
                                DBContract.FeedEntry.USERNAME));
                        Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                        intent.putExtra("key_name", username);
                        intent.putExtra("key_email", inputEmail);

                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity2.this,
                                "Email and Password do not match", Toast.LENGTH_SHORT).show();
                    }
                    clearText();
                    */
                }
            }
        });
        mRegister = findViewById(R.id.login_register_text);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity2.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void clearText() {
        mEmailView.setText("");
        mPasswordView.setText("");
    }
}