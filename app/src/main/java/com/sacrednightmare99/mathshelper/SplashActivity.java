package com.sacrednightmare99.mathshelper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.sacrednightmare99.mathshelper.Settings.UserSettings;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private UserSettings userSettings;
    private View parentView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userSettings = (UserSettings) getApplication();
        actionBar = getSupportActionBar();
        parentView = findViewById(R.id.parentView);

        loadSharedPreferences();

        int SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
    }

    private void loadSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = preferences.getString(UserSettings.CUSTOM_THEME, UserSettings.NO_THEME);
        userSettings.setCustomTheme(theme);
        updateView();
    }

    private void updateView() {
        final int white = ContextCompat.getColor(this, R.color.white);
        final int red = ContextCompat.getColor(this, R.color.red);
        final int blue = ContextCompat.getColor(this, R.color.blue);

        switch (userSettings.getCustomTheme()) {
            case UserSettings.NO_THEME:
                parentView.setBackgroundColor(white);
                actionBar.setBackgroundDrawable(new ColorDrawable(white));
                break;
            case UserSettings.RED_THEME:
                parentView.setBackgroundColor(red);
                actionBar.setBackgroundDrawable(new ColorDrawable(red));
                break;
            case UserSettings.BLUE_THEME:
                parentView.setBackgroundColor(blue);
                actionBar.setBackgroundDrawable(new ColorDrawable(blue));
                break;
        }
    }

}
