package com.example.myapplication;

import android.content.Context;
import android.view.View;

public class Docter extends View {
    private String name;
    private String password;

    public Docter(Context context) {
        super(context);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
