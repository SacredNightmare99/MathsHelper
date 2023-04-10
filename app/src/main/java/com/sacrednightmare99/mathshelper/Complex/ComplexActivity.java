package com.sacrednightmare99.mathshelper.Complex;

import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.ActivityClass;
import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class ComplexActivity extends ActivityClass {

    private TextView cmplxNumberSolView, polarSolView, eulerSolView, conjugateSolView, modSolView, argSolView;
    private Button backBtn, solveBtn;
    private EditText realPartET, imagePartET;
    private View parentView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);

        UserSettings userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences(userSettings, parentView, actionBar);

        backBtn.setOnClickListener(View -> finish());

        solveBtn.setOnClickListener(View -> {
            cmplxNumberSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            polarSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            eulerSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            conjugateSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            modSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            argSolView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            solve();
        });

        updateTextSize(cmplxNumberSolView);
        updateTextSize(polarSolView);
        updateTextSize(eulerSolView);
        updateTextSize(conjugateSolView);
        updateTextSize(modSolView);
        updateTextSize(argSolView);
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
        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();
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
        String complexSol = "Complex Number: " + z;
        cmplxNumberSolView.setText(complexSol);
        String polarSol = "Polar form: " + z.toPolarString();
        polarSolView.setText(polarSol);
        String conjSol = "Conjugate: " + z.conjugate();
        conjugateSolView.setText(conjSol);
        String modSol = "Modulus: " + z.modulus();
        modSolView.setText(modSol);
        String argSol = "Argument: " + z.argument();
        argSolView.setText(argSol);
        String eulerSol = "Euler's Form: " + z.toEulerString();
        eulerSolView.setText(eulerSol);
    }

    private void updateTextSize(TextView textView) {
        final float[] textSize = {16};
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (textView.getLineCount() > 1) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize[0]--);
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}