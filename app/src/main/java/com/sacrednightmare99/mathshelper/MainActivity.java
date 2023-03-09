package com.sacrednightmare99.mathshelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sacrednightmare99.mathshelper.Complex.ComplexActivity;
import com.sacrednightmare99.mathshelper.Degree2.Degree2MenuActivity;
import com.sacrednightmare99.mathshelper.Degree3.Degree3MenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button degree2Btn = findViewById(R.id.degree2Btn);
        degree2Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree2MenuActivity.class)));

        Button degree3Btn = findViewById(R.id.degree3Btn);
        degree3Btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Degree3MenuActivity.class)));

        Button cmplxNumbersBtn = findViewById(R.id.cmplxNumbersBtn);
        cmplxNumbersBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ComplexActivity.class)));
    }
}