package com.sacrednightmare99.mathshelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.Complex.ComplexActivity;
import com.sacrednightmare99.mathshelper.Degree2.Degree2MenuActivity;
import com.sacrednightmare99.mathshelper.Degree3.Degree3MenuActivity;
import com.sacrednightmare99.mathshelper.Settings.SettingsActivity;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class MainActivity extends AppCompatActivity {

    private UserSettings userSettings;
    private ActionBar actionBar;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSettings = (UserSettings) getApplication();

        actionBar = getSupportActionBar();
        parentView = findViewById(R.id.parentView);

        loadSharedPreferences();

        Button degree2Btn = findViewById(R.id.degree2Btn);
        degree2Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree2MenuActivity.class)));

        Button degree3Btn = findViewById(R.id.degree3Btn);
        degree3Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree3MenuActivity.class)));

        Button cmplxNumbersBtn = findViewById(R.id.cmplxNumbersBtn);
        cmplxNumbersBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ComplexActivity.class)));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settingsBtn) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
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