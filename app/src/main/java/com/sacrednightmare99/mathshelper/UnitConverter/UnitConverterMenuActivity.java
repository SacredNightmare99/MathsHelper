package com.sacrednightmare99.mathshelper.UnitConverter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class UnitConverterMenuActivity extends AppCompatActivity {

    private UserSettings userSettings;
    private ActionBar actionBar;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter_menu);

        userSettings = (UserSettings) getApplication();

        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();

        loadSharedPreferences();

        Button backBtn = findViewById(R.id.unitConvertBackBtn);
        backBtn.setOnClickListener(View -> finish());

        Button lengthBtn = findViewById(R.id.lengthConvertBtn);
        lengthBtn.setOnClickListener(View -> startActivity(new Intent(UnitConverterMenuActivity.this, LengthConverterActivity.class)));

        Button speedBtn = findViewById(R.id.speedConvertBtn);
        speedBtn.setOnClickListener(View -> startActivity(new Intent(UnitConverterMenuActivity.this, SpeedConverterActivity.class)));
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