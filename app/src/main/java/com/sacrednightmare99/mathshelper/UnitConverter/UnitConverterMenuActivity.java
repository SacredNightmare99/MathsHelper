package com.sacrednightmare99.mathshelper.UnitConverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.ActivityClass;
import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class UnitConverterMenuActivity extends ActivityClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter_menu);

        UserSettings userSettings = (UserSettings) getApplication();

        View parentView = findViewById(R.id.parentView);
        ActionBar actionBar = getSupportActionBar();

        loadSharedPreferences(userSettings, parentView, actionBar);

        Button backBtn = findViewById(R.id.unitConvertBackBtn);
        backBtn.setOnClickListener(View -> finish());

        Button lengthBtn = findViewById(R.id.lengthConvertBtn);
        lengthBtn.setOnClickListener(View -> startActivity(new Intent(UnitConverterMenuActivity.this, LengthConverterActivity.class)));

        Button speedBtn = findViewById(R.id.speedConvertBtn);
        speedBtn.setOnClickListener(View -> startActivity(new Intent(UnitConverterMenuActivity.this, SpeedConverterActivity.class)));
    }
}