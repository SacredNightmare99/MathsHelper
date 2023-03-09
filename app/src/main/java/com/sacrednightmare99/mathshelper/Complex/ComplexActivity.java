package com.sacrednightmare99.mathshelper.Complex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

public class ComplexActivity extends AppCompatActivity {

    private TextView cmplxNumberSolView, polarSolView, eulerSolView, conjugateSolView, modSolView, argSolView;
    private Button backBtn, solveBtn;
    private EditText realPartET, imagePartET;
    private View parentView;
    private UserSettings userSettings;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();

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