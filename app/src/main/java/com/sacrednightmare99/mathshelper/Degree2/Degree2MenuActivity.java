package com.sacrednightmare99.mathshelper.Degree2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.ActivityClass;
import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class Degree2MenuActivity extends ActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree2_menu);

        UserSettings userSettings = (UserSettings) getApplication();

        ActionBar actionBar = getSupportActionBar();
        View parentView = findViewById(R.id.parentView);

        loadSharedPreferences(userSettings, parentView, actionBar);

        Button rootsBtn = findViewById(R.id.degree2RootsBtn);
        rootsBtn.setOnClickListener(v -> startActivity(new Intent(Degree2MenuActivity.this, Degree2RootsActivity.class)));

        Button equationBtn = findViewById(R.id.degree2EquationBtn);
        equationBtn.setOnClickListener(v -> startActivity(new Intent(Degree2MenuActivity.this, Degree2EquationActivity.class)));

        Button backBtn = findViewById(R.id.degree2MenuBackBtn);
        backBtn.setOnClickListener(View -> finish());
    }
}