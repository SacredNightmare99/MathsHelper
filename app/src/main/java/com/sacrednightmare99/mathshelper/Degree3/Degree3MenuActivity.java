package com.sacrednightmare99.mathshelper.Degree3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.ActivityClass;
import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class Degree3MenuActivity extends ActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree3_menu);

        UserSettings userSettings = (UserSettings) getApplication();

        View parentView = findViewById(R.id.parentView);
        ActionBar actionBar = getSupportActionBar();

        loadSharedPreferences(userSettings, parentView, actionBar);

        Button backBtn = findViewById(R.id.degree3MenuBackBtn);
        backBtn.setOnClickListener(View -> finish());

        Button equationBtn = findViewById(R.id.degree3EquationBtn);
        equationBtn.setOnClickListener(View -> startActivity(new Intent(Degree3MenuActivity.this, Degree3EquationActivity.class)));
    }
}