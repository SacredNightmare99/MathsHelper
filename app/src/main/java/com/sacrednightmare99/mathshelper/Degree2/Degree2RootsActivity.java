package com.sacrednightmare99.mathshelper.Degree2;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;

import java.text.DecimalFormat;

public class Degree2RootsActivity extends AppCompatActivity {
    private TextView solutionView;
    private EditText coeffA, coeffB, coeffC;
    private Button backBtn, solveBtn;
    private UserSettings userSettings;
    private ActionBar actionBar;
    private View parentView;
    private String solution = "Root1:   Root2:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree2_roots);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();

        solutionView.setText(solution);

        backBtn.setOnClickListener(View -> finish());

        solveBtn.setOnClickListener(View -> {
            solutionView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            solve();
            solutionView.setText(solution);
        });

        // To change the font size
        updateTextSize(solutionView);
    }

    private void initWidgets() {
        solutionView = findViewById(R.id.degree2RootsSolutionView);
        coeffA = findViewById(R.id.coeff2A);
        coeffB = findViewById(R.id.coeff2B);
        coeffC = findViewById(R.id.coeff2C);
        backBtn = findViewById(R.id.degree2RootsBackBtn);
        solveBtn = findViewById(R.id.degree2RootsSolveBtn);
        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();
    }

    private void solve() {
        DecimalFormat df = new DecimalFormat("#.##");
        double a, b, c;
        double x1, x2;

        // Validate
        if (coeffA.getText().toString().isEmpty() || coeffB.getText().toString().isEmpty() || coeffC.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide all the Coefficients of the equation", Toast.LENGTH_SHORT).show();
            return;
        }
        if (coeffA.getText().toString().equals("0")) {
            Toast.makeText(this, "Not a 2-Degree Equation!", Toast.LENGTH_SHORT).show();
            return;
        }

        a = Double.parseDouble(coeffA.getText().toString());
        b = Double.parseDouble(coeffB.getText().toString());
        c = Double.parseDouble(coeffC.getText().toString());

        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            solution = "Root1: " + df.format(x1) + "  Root2: " + df.format(x2);
        } else if (discriminant == 0) {
            x1 = -b / (2 * a);
            solution = "Root: " + df.format(x1);
        } else if (discriminant < 0) {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            if (imaginaryPart > 0) {
                solution = "Roots: " + df.format(realPart) + " ± " + df.format(imaginaryPart) + "i";
            } else {
                solution = "Roots: " + df.format(realPart) + " ± " + df.format(-imaginaryPart) + "i";
            }
        } else {
            solution = "ERROR";
        }
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
        textView.setMaxLines(1);
        final float[] size = {(float) 20};
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(textView.getLineCount() > 1){
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size[0]--);
                }

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }
}