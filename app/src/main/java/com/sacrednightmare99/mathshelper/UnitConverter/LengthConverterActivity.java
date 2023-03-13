package com.sacrednightmare99.mathshelper.UnitConverter;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
    private Length length;

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

        CustomAdapter unitsAdapter = new CustomAdapter(
                this,
                R.layout.spinner_custom,
                R.layout.spinner_custom_dropdown,
                unitsList
        );

        firstUnitSP.setAdapter(unitsAdapter);
        secondUnitSP.setAdapter(unitsAdapter);

        firstUnitSPListener();
        secondUnitSPListener();

        solveBtn.setOnClickListener(View -> {
            if (input.getText().toString().isEmpty()) {
                Toast.makeText(this, "Provide some Value", Toast.LENGTH_SHORT).show();
                return;
            }
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
        length = new Length();
    }

    private void checkWhatToConvert() {
        if (firstUnitSP.getSelectedItem().equals(secondUnitSP.getSelectedItem()) || secondUnitSP.getSelectedItem().equals(firstUnitSP.getSelectedItem())) {
            length.setCurrentConversion(Length.SAME_UNITS);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            length.setCurrentConversion(Length.METER_TO_KILOMETER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            length.setCurrentConversion(Length.METER_TO_FEET);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            length.setCurrentConversion(Length.METER_TO_INCHES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Meters") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            length.setCurrentConversion(Length.METER_TO_MILES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            length.setCurrentConversion(Length.KILOMETER_TO_METER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            length.setCurrentConversion(Length.KILOMETER_TO_FEET);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            length.setCurrentConversion(Length.KILOMETER_TO_INCHES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Kilometers") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            length.setCurrentConversion(Length.KILOMETER_TO_MILES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            length.setCurrentConversion(Length.FEET_TO_METER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            length.setCurrentConversion(Length.FEET_TO_KILOMETER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            length.setCurrentConversion(Length.FEET_TO_INCHES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Feet") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            length.setCurrentConversion(Length.FEET_TO_MILES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            length.setCurrentConversion(Length.INCHES_TO_METER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            length.setCurrentConversion(Length.INCHES_TO_KILOMETER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            length.setCurrentConversion(Length.INCHES_TO_FEET);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Inches") && secondUnitSP.getSelectedItem().toString().equals("Miles")) {
            length.setCurrentConversion(Length.INCHES_TO_MILES);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Meters")) {
            length.setCurrentConversion(Length.MILES_TO_METER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Kilometers")) {
            length.setCurrentConversion(Length.MILES_TO_KILOMETER);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Feet")) {
            length.setCurrentConversion(Length.MILES_TO_FEET);
        } else if (firstUnitSP.getSelectedItem().toString().equals("Miles") && secondUnitSP.getSelectedItem().toString().equals("Inches")) {
            length.setCurrentConversion(Length.MILES_TO_INCHES);
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

        switch (length.getCurrentConversion()) {
            case Length.SAME_UNITS:
                convertSame();
                break;
            case Length.METER_TO_KILOMETER:
                meterToKilometer();
                break;
            case Length.METER_TO_FEET:
                meterToFeet();
                break;
            case Length.METER_TO_INCHES:
                meterToInches();
                break;
            case Length.METER_TO_MILES:
                meterToMiles();
                break;
            case Length.KILOMETER_TO_METER:
                kilometerToMeter();
                break;
            case Length.KILOMETER_TO_FEET:
                kilometerToFeet();
                break;
            case Length.KILOMETER_TO_INCHES:
                kilometerToInches();
                break;
            case Length.KILOMETER_TO_MILES:
                kilometerToMiles();
                break;
            case Length.FEET_TO_METER:
                feetToMeter();
                break;
            case Length.FEET_TO_KILOMETER:
                feetToKilometer();
                break;
            case Length.FEET_TO_INCHES:
                feetToInches();
                break;
            case Length.FEET_TO_MILES:
                feetToMiles();
                break;
            case Length.INCHES_TO_METER:
                inchesToMeter();
                break;
            case Length.INCHES_TO_KILOMETER:
                inchesToKilometer();
                break;
            case Length.INCHES_TO_FEET:
                inchesToFeet();
                break;
            case Length.INCHES_TO_MILES:
                inchesToMiles();
                break;
            case Length.MILES_TO_METER:
                milesToMeter();
                break;
            case Length.MILES_TO_KILOMETER:
                milesToKilometer();
                break;
            case Length.MILES_TO_FEET:
                milesToFeet();
                break;
            case Length.MILES_TO_INCHES:
                milesToInches();
                break;
            default:
                solution = "ERROR!";
                break;
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