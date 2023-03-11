package com.sacrednightmare99.mathshelper.Degree3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

import java.text.DecimalFormat;

public class Degree3EquationActivity extends AppCompatActivity {

    private TextView solutionView;
    private EditText realRoot1, realRoot2, realRoot3, unrealRootRealRoot, unrealRootRP, unrealRootIP;
    private RadioGroup radioGroup;
    private Button solveBtn, backBtn;
    private String solution = "Ax³ + Bx² + Cx + D = 0";
    private final DecimalFormat df = new DecimalFormat("#.##");
    private UserSettings userSettings;
    private View parentView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree3_equation);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();

        backBtn.setOnClickListener(View -> finish());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.degree3RealRootsRB) {
                showReal();
            } else if (checkedId == R.id.degree3UnrealRootsRB) {
                showUnreal();
            } else {
                solution = "ERROR";
            }
            solutionView.setText(solution);
        });

        solveBtn.setOnClickListener(View -> {
            int checkedRBID = radioGroup.getCheckedRadioButtonId();
            if (checkedRBID == R.id.degree3RealRootsRB) {
                solveReal();
            } else if (checkedRBID == R.id.degree3UnrealRootsRB) {
                solveUnreal();
            } else {
                solution = "ERROR!";
            }
            solutionView.setText(solution);
        });

        updateTextSize(solutionView);
    }

    private void solveReal() {
        //Validate
        if (realRoot1.getText().toString().isEmpty() || realRoot2.getText().toString().isEmpty() || realRoot3.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide all values", Toast.LENGTH_SHORT).show();
            return;
        }

        double x = Double.parseDouble(realRoot1.getText().toString());
        double y = Double.parseDouble(realRoot2.getText().toString());
        double z = Double.parseDouble(realRoot3.getText().toString());

        double B = -(x+y+z);
        double C = x*y + x*z + y*z;
        double D = -(x*y*z);

        // Set the solution for all possible types of values
        if (B < 0 && C < 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C < 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B < 0 && C > 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B < 0 && C < 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B > 0 && C > 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C < 0 && D > 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B < 0 && C > 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C == 0 && D == 0) {
            solution = "x³ = 0";
        } else if (B == 0 && C > 0 && D > 0) {
            solution = "x³ + " + df.format(C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C < 0 && D > 0) {
            solution = "x³ - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C > 0 && D < 0) {
            solution = "x³ + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B == 0 && C < 0 && D < 0) {
            solution = "x³ - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C == 0 && D > 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(D) + " = 0";
        } else if (B < 0 && C == 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(D) + " = 0";
        } else if (B > 0 && C == 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-D) + " = 0";
        } else if (B < 0 && C == 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-D) + " = 0";
        } else if (B > 0 && C > 0 && D == 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x = 0";
        } else if (B < 0 && C > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x = 0";
        } else if (B > 0 && C < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x = 0";
        } else if (B < 0 && C < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x = 0";
        } else if (B == 0 && C == 0 && D > 0) {
            solution = "x³ + " + df.format(D) + " = 0";
        } else if (B == 0 && C == 0 && D < 0) {
            solution = "x³ - " + df.format(-D) + " = 0";
        } else if (B > 0 && C == 0) {
            solution = "x³ + " + df.format(B) + "x² = 0";
        } else if (B < 0) {
            solution = "x³ - " + df.format(-B) + "x² = 0";
        } else if (B == 0 && C > 0) {
            solution = "x³ + " + df.format(C) + "x = 0";
        } else if (B == 0 && C < 0) {
            solution = "x³ - " + df.format(-C) + "x = 0";
        } else {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x + " + df.format(D) + " = 0";
        }
    }

    private void solveUnreal() {
        //Validate
        if (unrealRootRealRoot.getText().toString().isEmpty() || unrealRootRP.getText().toString().isEmpty() || unrealRootIP.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide all values", Toast.LENGTH_SHORT).show();
            return;
        }

        double x = Double.parseDouble(unrealRootRealRoot.getText().toString());
        double xR = Double.parseDouble(unrealRootRP.getText().toString());
        double xI = Double.parseDouble(unrealRootIP.getText().toString());

        double B = -(x + xR + xR);
        double C = (2*x*xR) + (xR*xR) + (xI*xI);
        double D = -(x*(xR*xR + xI*xI));

        // Set the solution for all possible types of values
        if (B < 0 && C < 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C < 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B < 0 && C > 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B < 0 && C < 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B > 0 && C > 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C < 0 && D > 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B < 0 && C > 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C == 0 && D == 0) {
            solution = "x³ = 0";
        } else if (B == 0 && C > 0 && D > 0) {
            solution = "x³ + " + df.format(C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C < 0 && D > 0) {
            solution = "x³ - " + df.format(-C) + "x + " + df.format(D) + " = 0";
        } else if (B == 0 && C > 0 && D < 0) {
            solution = "x³ + " + df.format(C) + "x - " + df.format(-D) + " = 0";
        } else if (B == 0 && C < 0 && D < 0) {
            solution = "x³ - " + df.format(-C) + "x - " + df.format(-D) + " = 0";
        } else if (B > 0 && C == 0 && D > 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(D) + " = 0";
        } else if (B < 0 && C == 0 && D > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(D) + " = 0";
        } else if (B > 0 && C == 0 && D < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-D) + " = 0";
        } else if (B < 0 && C == 0 && D < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-D) + " = 0";
        } else if (B > 0 && C > 0 && D == 0) {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x = 0";
        } else if (B < 0 && C > 0) {
            solution = "x³ - " + df.format(-B) + "x² + " + df.format(C) + "x = 0";
        } else if (B > 0 && C < 0) {
            solution = "x³ + " + df.format(B) + "x² - " + df.format(-C) + "x = 0";
        } else if (B < 0 && C < 0) {
            solution = "x³ - " + df.format(-B) + "x² - " + df.format(-C) + "x = 0";
        } else if (B == 0 && C == 0 && D > 0) {
            solution = "x³ + " + df.format(D) + " = 0";
        } else if (B == 0 && C == 0 && D < 0) {
            solution = "x³ - " + df.format(-D) + " = 0";
        } else if (B > 0 && C == 0) {
            solution = "x³ + " + df.format(B) + "x² = 0";
        } else if (B < 0) {
            solution = "x³ - " + df.format(-B) + "x² = 0";
        } else if (B == 0 && C > 0) {
            solution = "x³ + " + df.format(C) + "x = 0";
        } else if (B == 0 && C < 0) {
            solution = "x³ - " + df.format(-C) + "x = 0";
        } else {
            solution = "x³ + " + df.format(B) + "x² + " + df.format(C) + "x + " + df.format(D) + " = 0";
        }

    }

    private void initWidgets() {
        solutionView = findViewById(R.id.degree3EquationSolution);
        realRoot1 = findViewById(R.id.degree3RealRoot1);
        realRoot2 = findViewById(R.id.degree3RealRoot2);
        realRoot3 = findViewById(R.id.degree3RealRoot3);
        unrealRootRealRoot = findViewById(R.id.degree3UnrealRootReal);
        unrealRootRP = findViewById(R.id.degree3UnrealRootImageRP);
        unrealRootIP = findViewById(R.id.degree3UnrealRootImageIP);
        radioGroup = findViewById(R.id.degree3RG);
        solveBtn = findViewById(R.id.degree3EquationSolveBtn);
        backBtn = findViewById(R.id.degree3EquationBackBtn);
        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();
    }

    private void showReal() {
        realRoot1.setVisibility(View.VISIBLE);
        realRoot2.setVisibility(View.VISIBLE);
        realRoot3.setVisibility(View.VISIBLE);
        unrealRootRealRoot.setVisibility(View.GONE);
        unrealRootRP.setVisibility(View.GONE);
        unrealRootIP.setVisibility(View.GONE);
        solution = "Ax³ + Bx² + Cx + D = 0";
        resetET();
    }

    private void showUnreal() {
        realRoot1.setVisibility(View.GONE);
        realRoot2.setVisibility(View.GONE);
        realRoot3.setVisibility(View.GONE);
        unrealRootRealRoot.setVisibility(View.VISIBLE);
        unrealRootRP.setVisibility(View.VISIBLE);
        unrealRootIP.setVisibility(View.VISIBLE);
        solution = "Ax³ + Bx² + Cx + D = 0";
        resetET();
    }

    private void resetET() {
        realRoot1.setText("");
        realRoot2.setText("");
        realRoot3.setText("");
        unrealRootRealRoot.setText("");
        unrealRootRP.setText("");
        unrealRootIP.setText("");
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

    private void updateTextSize(TextView textView) {
        final float[] textSize = {18};
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(textView.getLineCount() > 1){
                    textView.setTextSize(textSize[0]--);
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}