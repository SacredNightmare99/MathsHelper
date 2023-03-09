package com.sacrednightmare99.mathshelper.Degree3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sacrednightmare99.mathshelper.R;

public class Degree3MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree3_menu);

        Button backBtn = findViewById(R.id.degree3MenuBackBtn);
        backBtn.setOnClickListener(View -> finish());

        Button equationBtn = findViewById(R.id.degree3EquationBtn);
        equationBtn.setOnClickListener(View -> startActivity(new Intent(Degree3MenuActivity.this, Degree3EquationActivity.class)));
    }
}