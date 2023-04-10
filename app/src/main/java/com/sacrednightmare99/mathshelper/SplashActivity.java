package com.sacrednightmare99.mathshelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.Settings.UserSettings;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends ActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserSettings userSettings = (UserSettings) getApplication();
        ActionBar actionBar = getSupportActionBar();
        View parentView = findViewById(R.id.parentView);

        loadSharedPreferences(userSettings, parentView, actionBar);

        int SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
    }

}
