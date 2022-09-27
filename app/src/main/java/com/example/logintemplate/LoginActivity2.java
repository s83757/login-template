package com.example.logintemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    private EditText mEmailView, mPasswordView;
    private Button mSignInButton;
    private TextView mRegister;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDBHelper = new DBHelper(this);
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        mSignInButton = findViewById(R.id.login_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = mEmailView.getText().toString().trim();
                String inputPassword = mPasswordView.getText().toString().trim();

                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity2.this, "Empty Field",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //all fields are complete
                    // if email is an actual email, and password and email match.
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
                }
            }
        });
        mRegister = findViewById(R.id.register_text);
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