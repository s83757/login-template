package com.example.logintemplate;

import android.provider.BaseColumns;

public class DBContract {

    public DBContract(){

    }

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String NAME_NULLABLE = "null";
    }
}