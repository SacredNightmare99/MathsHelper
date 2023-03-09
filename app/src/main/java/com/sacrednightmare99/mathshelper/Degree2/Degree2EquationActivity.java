package com.sacrednightmare99.mathshelper.Degree2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

import java.text.DecimalFormat;

public class Degree2EquationActivity extends AppCompatActivity {

    private View parentView;
    private UserSettings userSettings;
    private ActionBar actionBar;
    private Button backBtn, solveBtn;
    private EditText realRoot1, realRoot2, unrealRootReal, unrealRootImage;
    private TextView solutionView;
    private RadioGroup radioGroup;
    private String solution = "Ax² + Bx + C = 0";
    private final DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree2_equation);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();

        backBtn.setOnClickListener(View -> finish());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.degree2RealRootsRB) {
                showReal();
            } else if (checkedId == R.id.degree2UnrealRootsRB) {
                showUnreal();
            } else {
                solution = "ERROR!";
            }
            solutionView.setText(solution);
        });

        solveBtn.setOnClickListener(v -> {

            int checkedRBID = radioGroup.getCheckedRadioButtonId();
            if (checkedRBID == R.id.degree2RealRootsRB) {
                solveReal();
            } else if (checkedRBID == R.id.degree2UnrealRootsRB) {
                solveUnreal();
            } else {
                solution = "ERROR!";
            }
            solutionView.setText(solution);
        });
    }

    private void solveReal() {
        //Validate
        if (realRoot1.getText().toString().isEmpty() || realRoot2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide all values!", Toast.LENGTH_SHORT).show();
            return;
        }

        double x = Double.parseDouble(realRoot1.getText().toString());
        double y = Double.parseDouble(realRoot2.getText().toString());

        double B = -(x+y);
        double C = (x*y);

        if (B < 0 && C < 0) {
            solution = "x² - " + df.format(-B) + "x - " + df.format(-C) + " = 0";
        } else if (B < 0 && C > 0) {
            solution = "x² - " + df.format(-B) + "x + " + df.format(C) + " = 0";
        } else if (B > 0 && C < 0) {
            solution = "x² + " + df.format(B) + "x - " + df.format(-C) + " = 0";
        } else {
            solution = "x² + " + df.format(B) + "x + " + df.format(C) + " = 0";
        }
    }

    private void solveUnreal() {
        //Validate
        if (unrealRootReal.getText().toString().isEmpty() || unrealRootImage.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide all values!", Toast.LENGTH_SHORT).show();
            return;
        }

        double xR = Double.parseDouble(unrealRootReal.getText().toString());
        double xI = Double.parseDouble(unrealRootImage.getText().toString());

        double B = -(2*xR);
        double C = (xR*xR) + (xI*xI);

        if (B < 0 && C < 0) {
            solution = "x² - " + df.format(-B) + "x - " + df.format(-C) + " = 0";
        } else if (B < 0 && C > 0) {
            solution = "x² - " + df.format(-B) + "x + " + df.format(C) + " = 0";
        } else if (B > 0 && C < 0) {
            solution = "x² + " + df.format(B) + "x - " + df.format(-C) + " = 0";
        } else {
            solution = "x² + " + df.format(B) + "x + " + df.format(C) + " = 0";
        }
    }

    private void initWidgets() {
        backBtn = findViewById(R.id.degree2EquationBackBtn);
        solveBtn = findViewById(R.id.degree2EquationSolveBtn);
        realRoot1 = findViewById(R.id.degree2RealRoot1);
        realRoot2 = findViewById(R.id.degree2RealRoot2);
        unrealRootReal = findViewById(R.id.degree2UnrealRootReal);
        unrealRootImage = findViewById(R.id.degree2UnrealRootImage);
        solutionView = findViewById(R.id.degree2EquationSolution);
        radioGroup = findViewById(R.id.degree2RG);
        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();
    }
    private void showReal() {
        realRoot1.setVisibility(View.VISIBLE);
        realRoot2.setVisibility(View.VISIBLE);
        unrealRootReal.setVisibility(View.GONE);
        unrealRootImage.setVisibility(View.GONE);
        resetET();
        solution = "Ax² + Bx + C = 0";
    }
    private void showUnreal() {
        realRoot1.setVisibility(View.GONE);
        realRoot2.setVisibility(View.GONE);
        unrealRootReal.setVisibility(View.VISIBLE);
        unrealRootImage.setVisibility(View.VISIBLE);
        resetET();
        solution = "Ax² + Bx + C = 0";
    }
    private void resetET() {
        realRoot1.setText("");
        realRoot2.setText("");
        unrealRootReal.setText("");
        unrealRootImage.setText("");
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