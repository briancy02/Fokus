package com.example.fokus;

import android.app.Application;

import com.backendless.Backendless;

public class ApplicationClass extends Application {
    public static final String APPLICATION_ID = "BC11B438-4778-DDBA-FF6C-BFECE07FA900";
    public static final String API_KEY = "6CC41866-9B97-4E73-B787-328D1DABA6E7";
    public static final String SERVER_URL = "http://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );
    }
}
