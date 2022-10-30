package com.example.MentorMarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsernameView, mEmailView, mPasswordView;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDBHelper = new DBHelper(this);
        mUsernameView = findViewById(R.id.register_username);
        mEmailView = findViewById(R.id.register_email);
        mPasswordView = findViewById(R.id.register_password);

        Button mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameView.getText().toString().trim();
                String email = mEmailView.getText().toString().trim();
                String password = mPasswordView.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Empty Field",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase readDB = mDBHelper.getReadableDatabase();
                    Cursor cursor = readDB.rawQuery("SELECT * FROM " +
                            DBContract.FeedEntry.TABLE_NAME + " WHERE " +
                            DBContract.FeedEntry.EMAIL + " ='" + email + "'", null);

                    if (cursor.getCount() > 0) {
                        Toast.makeText(RegisterActivity.this,
                                "Account already exists under this email",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        SQLiteDatabase writeDB = mDBHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.put(DBContract.FeedEntry.USERNAME, username);
                        values.put(DBContract.FeedEntry.EMAIL, email);
                        values.put(DBContract.FeedEntry.PASSWORD, password);
                        long newRowId = writeDB.insert(DBContract.FeedEntry.TABLE_NAME,
                                DBContract.FeedEntry.NAME_NULLABLE, values);

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity2.class);

                    }
                }
            }
        });

        Button mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(v -> finish());

    }

    public void clearText() {
        mUsernameView.setText("");
        mEmailView.setText("");
        mPasswordView.setText("");
    }
}