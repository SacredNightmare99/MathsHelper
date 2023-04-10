package com.sacrednightmare99.mathshelper.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.MainActivity;
import com.sacrednightmare99.mathshelper.R;

public class SettingsActivity extends AppCompatActivity {

    private View parentView;
    private ActionBar actionBar;
    private UserSettings userSettings;
    private RadioGroup themeRG;
    private RadioButton noThemeRB, redThemeRB, blueThemeRB, greenThemeRB, yellowThemeRB, pinkThemeRB;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userSettings = (UserSettings) getApplication();

        initWidgets();
        loadSharedPreferences();
        themeChangeListener();

        backBtn.setOnClickListener(v -> {
            finishAffinity();
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        });
    }

    private void initWidgets() {
        parentView = findViewById(R.id.parentView);
        backBtn = findViewById(R.id.settingsBackBtn);
        themeRG = findViewById(R.id.settingsThemeRG);
        noThemeRB = findViewById(R.id.settingsNoThemeRB);
        redThemeRB = findViewById(R.id.settingsRedThemeRB);
        blueThemeRB = findViewById(R.id.settingsBlueThemeRB);
        greenThemeRB = findViewById(R.id.settingsGreenThemeRB);
        yellowThemeRB = findViewById(R.id.settingsYellowThemeRB);
        pinkThemeRB = findViewById(R.id.settingsPinkThemeRB);
        actionBar = getSupportActionBar();
    }

    private void loadSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = preferences.getString(UserSettings.CUSTOM_THEME, UserSettings.NO_THEME);
        userSettings.setCustomTheme(theme);
        updateView();
    }

    private void themeChangeListener() {
        themeRG.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.settingsNoThemeRB) {
                userSettings.setCustomTheme(UserSettings.NO_THEME);
            } else if (checkedId == R.id.settingsRedThemeRB) {
                userSettings.setCustomTheme(UserSettings.RED_THEME);
            } else if (checkedId == R.id.settingsBlueThemeRB) {
                userSettings.setCustomTheme(UserSettings.BLUE_THEME);
            } else if (checkedId == R.id.settingsGreenThemeRB) {
                userSettings.setCustomTheme(UserSettings.GREEN_THEME);
            } else if (checkedId == R.id.settingsYellowThemeRB) {
                userSettings.setCustomTheme(UserSettings.YELLOW_THEME);
            } else if (checkedId == R.id.settingsPinkThemeRB) {
                userSettings.setCustomTheme(UserSettings.PINK_THEME);
            }

            SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE).edit();
            editor.putString(UserSettings.CUSTOM_THEME, userSettings.getCustomTheme());
            editor.apply();
            updateView();
        });
    }

    private void updateView() {
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
                noThemeRB.setChecked(true);
                break;
            case UserSettings.RED_THEME:
                parentView.setBackgroundColor(red);
                actionBar.setBackgroundDrawable(new ColorDrawable(red));
                redThemeRB.setChecked(true);
                break;
            case UserSettings.BLUE_THEME:
                parentView.setBackgroundColor(blue);
                actionBar.setBackgroundDrawable(new ColorDrawable(blue));
                blueThemeRB.setChecked(true);
                break;
            case UserSettings.GREEN_THEME:
                parentView.setBackgroundColor(green);
                actionBar.setBackgroundDrawable(new ColorDrawable(green));
                greenThemeRB.setChecked(true);
                break;
            case UserSettings.YELLOW_THEME:
                parentView.setBackgroundColor(yellow);
                actionBar.setBackgroundDrawable(new ColorDrawable(yellow));
                yellowThemeRB.setChecked(true);
                break;
            case UserSettings.PINK_THEME:
                parentView.setBackgroundColor(pink);
                actionBar.setBackgroundDrawable(new ColorDrawable(pink));
                pinkThemeRB.setChecked(true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }
}