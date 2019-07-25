package com.example.hsh.homesweethome;

import android.app.Application;
import android.content.Context;

public class Main extends Application {

    private static Context context;
    private static Main instance;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return Main.context;
    }
}