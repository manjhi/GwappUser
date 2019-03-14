package com.omninos.gwappuser.Utils;

import android.app.Application;

public class App extends Application {
    private static App instance;

    private static AppPreferences appPreferences;

    public static App getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferences=new AppPreferences();
    }

    public static AppPreferences getAppPreferences() {
        return appPreferences;
    }
}
