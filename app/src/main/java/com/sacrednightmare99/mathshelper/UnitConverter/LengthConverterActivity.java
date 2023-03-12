package com.sacrednightmare99.mathshelper.UnitConverter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;
import com.sacrednightmare99.mathshelper.UnitConverter.Converters.Length;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LengthConverterActivity extends AppCompatActivity {

    private final DecimalFormat df = new DecimalFormat("#.#####");
    private View parentView;
    private ActionBar actionBar;
    private UserSettings userSettings;
    private Spinner firstUnitSP, secondUnitSP;
    private Button backBtn, solveBtn;
    private TextView solutionView;
    private EditText input;
    private String solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_converter);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();


        backBtn.setOnClickListener(View -> finish());

        ArrayList<String> unitsList = new ArrayList<>();
        unitsList.add("Meters");
        unitsList.add("Kilometers");
        unitsList.add("Feet");
        unitsList.add("Inches");
        unitsList.add("Miles");

        ArrayAdapter<String> unitsAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_custom,
                unitsList
        );
        unitsAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        firstUnitSP.setAdapter(unitsAdapter);
        secondUnitSP.setAdapter(unitsAdapter);

        firstUnitSPListener();
        secondUnitSPListener();

        solveBtn.setOnClickListener(View -> {
            solve();
            solutionView.setText(solution);
        });
    }

    private void initWidgets() {
        parentView = findViewById(R.id.parentView);
        actionBar = getSupportActionBar();
        firstUnitSP = findViewById(R.id.lengthConverter1SP);
        secondUnitSP = findViewById(R.id.lengthConverter2SP);
        backBtn = findViewById(R.id.lengthConverterBackBtn);
        solveBtn = findViewById(R.id.lengthConverterSolveBtn);
        solutionView = findViewById(R.id.lengthConverterOutput);
        input = findViewById(R.id.lengthConverterInput);
    }

    private void checkWhatToConvert() {
        if (firstUnitSP.getSelectedItem().equals(secondUnitSP.getSelectedItem()) || secondUnitSP.getSelectedItem().equals(firstUnitSP.getSelectedItem())) {
            Length.setAllFalse();
            Length.setSameUnits(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            Length.setAllFalse();
            Length.setMeterToKilometer(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            Length.setAllFalse();
            Length.setMeterToFeet(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            Length.setAllFalse();
            Length.setMeterToInches(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            Length.setAllFalse();
            Length.setMeterToMiles(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            Length.setAllFalse();
            Length.setKilometerToMeter(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            Length.setAllFalse();
            Length.setKilometerToFeet(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            Length.setAllFalse();
            Length.setKilometerToInches(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            Length.setAllFalse();
            Length.setKilometerToMiles(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            Length.setAllFalse();
            Length.setFeetToMeter(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            Length.setAllFalse();
            Length.setFeetToKilometer(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            Length.setAllFalse();
            Length.setFeetToInches(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            Length.setAllFalse();
            Length.setFeetToMiles(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            Length.setAllFalse();
            Length.setInchesToMeter(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            Length.setAllFalse();
            Length.setInchesToKilometer(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            Length.setAllFalse();
            Length.setInchesToFeet(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            Length.setAllFalse();
            Length.setInchesToMiles(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            Length.setAllFalse();
            Length.setMilesToMeter(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            Length.setAllFalse();
            Length.setMilesToKilometer(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            Length.setAllFalse();
            Length.setMilesToFeet(true);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            Length.setAllFalse();
            Length.setMilesToInches(true);
        } else {
            Length.setAllFalse();
        }
    }

    private void firstUnitSPListener() {
        firstUnitSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                solution = "";
                solutionView.setText(solution);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void secondUnitSPListener() {
        secondUnitSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                solution = "";
                solutionView.setText(solution);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    private void solve() {
        checkWhatToConvert();

        if (input.getText().toString().isEmpty()) {
            Toast.makeText(LengthConverterActivity.this, "Provide a value!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Length.isSameUnits()) {
            convertSame();
        } else if (Length.isMeterToKilometer()) {
            meterToKilometer();
        } else if (Length.isMeterToFeet()) {
            meterToFeet();
        } else if (Length.isMeterToInches()) {
            meterToInches();
        } else if (Length.isMeterToMiles()) {
            meterToMiles();
        } else if (Length.isKilometerToMeter()) {
            kilometerToMeter();
        } else if (Length.isKilometerToFeet()) {
            kilometerToFeet();
        } else if (Length.isKilometerToInches()) {
            kilometerToInches();
        } else if (Length.isKilometerToMiles()) {
            kilometerToMiles();
        } else if (Length.isFeetToMeter()) {
            feetToMeter();
        } else if (Length.isFeetToKilometer()) {
            feetToKilometer();
        } else if (Length.isFeetToInches()) {
            feetToInches();
        } else if (Length.isFeetToMiles()) {
            feetToMiles();
        } else if (Length.isInchesToMeter()) {
            inchesToMeter();
        } else if (Length.isInchesToKilometer()) {
            inchesToKilometer();
        } else if (Length.isInchesToFeet()) {
            inchesToFeet();
        } else if (Length.isInchesToMiles()) {
            inchesToMiles();
        } else if (Length.isMilesToMeter()) {
            milesToMeter();
        } else if (Length.isMilesToKilometer()) {
            milesToKilometer();
        } else if (Length.isMilesToFeet()) {
            milesToFeet();
        } else if (Length.isMilesToInches()) {
            milesToInches();
        } else {
            solution = "ERROR!";
        }
    }

    private void convertSame() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x);
    }
    private void meterToKilometer() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1000);
    }
    private void meterToFeet() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*3.281);
    }
    private void meterToInches() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*39.37);
    }
    private void meterToMiles() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1609);
    }
    private void kilometerToMeter() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1000);
    }
    private void kilometerToFeet() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*3281);
    }
    private void kilometerToInches() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*39370);
    }
    private void kilometerToMiles() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.609);
    }
    private void feetToMeter() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/3.281);
    }
    private void feetToKilometer() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/3281);
    }
    private void feetToInches() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*12);
    }
    private void feetToMiles() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/5280);
    }
    private void inchesToMeter() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/39.37);
    }
    private void inchesToKilometer() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/39370);
    }
    private void inchesToFeet() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/12);
    }
    private void inchesToMiles() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/63360);
    }
    private void milesToMeter() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1609);
    }
    private void milesToKilometer() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.609);
    }
    private void milesToFeet() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*5280);
    }
    private void milesToInches() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*63360);
    }
}