package com.sacrednightmare99.mathshelper.Complex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sacrednightmare99.mathshelper.R;

public class ComplexActivity extends AppCompatActivity {

    private TextView cmplxNumberSolView, polarSolView, eulerSolView, conjugateSolView, modSolView, argSolView;
    private Button backBtn, solveBtn;
    private EditText realPartET, imagePartET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);

        initWidgets();

        backBtn.setOnClickListener(View -> finish());

        solveBtn.setOnClickListener(View -> solve());
    }

    private void initWidgets() {
        cmplxNumberSolView = findViewById(R.id.cmplxNumberSolView);
        polarSolView = findViewById(R.id.polarSolView);
        eulerSolView = findViewById(R.id.eulerSolView);
        conjugateSolView = findViewById(R.id.conjugateCmplxSolView);
        modSolView = findViewById(R.id.modCmplxSolView);
        argSolView = findViewById(R.id.argCmplxSolView);
        backBtn = findViewById(R.id.cmplxBackBtn);
        solveBtn = findViewById(R.id.cmplxSolveBtn);
        realPartET = findViewById(R.id.cmplxRealPartET);
        imagePartET = findViewById(R.id.cmplxUnrealPartET);
    }

    private void solve() {
        double real, image;

        // Validation
        if (realPartET.getText().toString().isEmpty() || imagePartET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter values for both real and imaginary parts!", Toast.LENGTH_SHORT).show();
            return;
        }

        real = Double.parseDouble(realPartET.getText().toString());
        image = Double.parseDouble(imagePartET.getText().toString());

        Complex z = new Complex(real, image);
        cmplxNumberSolView.setText("Complex Number: " + z);
        polarSolView.setText("Polar form: " + z.toPolarString());
        conjugateSolView.setText("Conjugate: " + z.conjugate());
        modSolView.setText("Modulus: " + z.modulus());
        argSolView.setText("Argument: " + z.argument());
        eulerSolView.setText("Euler's Form: " + z.toEulerString());
    }
}