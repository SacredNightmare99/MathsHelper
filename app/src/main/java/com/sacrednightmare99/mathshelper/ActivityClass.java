package com.sacrednightmare99.mathshelper;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class ActivityClass extends AppCompatActivity {

    public void loadSharedPreferences(UserSettings userSettings, View parentView, ActionBar actionBar) {
        SharedPreferences preferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = preferences.getString(UserSettings.CUSTOM_THEME, UserSettings.NO_THEME);
        userSettings.setCustomTheme(theme);
        updateView(userSettings, parentView, actionBar);
    }

    public void updateView(UserSettings userSettings, View parentView, ActionBar actionBar) {
        final int white = ContextCompat.getColor(this, R.color.white);
        final int red = ContextCompat.getColor(this, R.color.red);
        final int blue = ContextCompat.getColor(this, R.color.blue);
        final int green = ContextCompat.getColor(this, R.color.green);
        final int yellow = ContextCompat.getColor(this, R.color.yellow);
        final int pink = ContextCompat.getColor(this, R.color.pink);

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
            case UserSettings.GREEN_THEME:
                parentView.setBackgroundColor(green);
                actionBar.setBackgroundDrawable(new ColorDrawable(green));
                break;
            case UserSettings.YELLOW_THEME:
                parentView.setBackgroundColor(yellow);
                actionBar.setBackgroundDrawable(new ColorDrawable(yellow));
                break;
            case UserSettings.PINK_THEME:
                parentView.setBackgroundColor(pink);
                actionBar.setBackgroundDrawable(new ColorDrawable(pink));
                break;
        }
    }

}
