package com.sacrednightmare99.mathshelper.Degree2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class Degree2MenuActivity extends AppCompatActivity {

    private View parentView;
    private ActionBar actionBar;
    private UserSettings userSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree2_menu);

        userSettings = (UserSettings) getApplication();

        actionBar = getSupportActionBar();
        parentView = findViewById(R.id.parentView);

        loadSharedPreferences();

        Button rootsBtn = findViewById(R.id.degree2RootsBtn);
        rootsBtn.setOnClickListener(v -> startActivity(new Intent(Degree2MenuActivity.this, Degree2RootsActivity.class)));

        Button equationBtn = findViewById(R.id.degree2EquationBtn);
        equationBtn.setOnClickListener(v -> startActivity(new Intent(Degree2MenuActivity.this, Degree2EquationActivity.class)));

        Button backBtn = findViewById(R.id.degree2MenuBackBtn);
        backBtn.setOnClickListener(View -> finish());
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