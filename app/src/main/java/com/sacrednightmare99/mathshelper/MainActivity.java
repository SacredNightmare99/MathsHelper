package com.sacrednightmare99.mathshelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.Complex.ComplexActivity;
import com.sacrednightmare99.mathshelper.Degree2.Degree2MenuActivity;
import com.sacrednightmare99.mathshelper.Degree3.Degree3MenuActivity;
import com.sacrednightmare99.mathshelper.Settings.SettingsActivity;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;
import com.sacrednightmare99.mathshelper.UnitConverter.UnitConverterMenuActivity;

public class MainActivity extends ActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserSettings userSettings = (UserSettings) getApplication();

        ActionBar actionBar = getSupportActionBar();
        View parentView = findViewById(R.id.parentView);

        loadSharedPreferences(userSettings, parentView, actionBar);

        Button degree2Btn = findViewById(R.id.degree2Btn);
        degree2Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree2MenuActivity.class)));

        Button degree3Btn = findViewById(R.id.degree3Btn);
        degree3Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree3MenuActivity.class)));

        Button cmplxNumbersBtn = findViewById(R.id.cmplxNumbersBtn);
        cmplxNumbersBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ComplexActivity.class)));

        Button unitConverterBtn = findViewById(R.id.unitConvertBtn);
        unitConverterBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UnitConverterMenuActivity.class)));
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

}