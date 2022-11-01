
package com.example.MentorMarketplace;

import android.app.Application;

public class MyApplication extends Application {

    private String self_id;

    public String getSelf_id() {
        return self_id;
    }

    public void setSelf_id(String self_id) {
        this.self_id = self_id;
    }
}
