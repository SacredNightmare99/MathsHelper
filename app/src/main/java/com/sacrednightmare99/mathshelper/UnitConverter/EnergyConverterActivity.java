package com.sacrednightmare99.mathshelper.UnitConverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.sacrednightmare99.mathshelper.R;
import com.sacrednightmare99.mathshelper.Settings.UserSettings;
import com.sacrednightmare99.mathshelper.UnitConverter.Converters.Energy;

import java.util.ArrayList;

public class EnergyConverterActivity extends Energy {

    private ActionBar actionBar;
    private View parentView;
    private Spinner firstUnitSP, secondUnitSP;
    private Button backBtn, solveBtn;
    private TextView solutionView;
    private EditText input;
    private String solution;
    private ArrayList<String> unitsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_converter);

        UserSettings userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences(userSettings, parentView, actionBar);

        backBtn.setOnClickListener(View -> finish());

        unitsList = new ArrayList<>();
        unitsList.add("Joules");
        unitsList.add("KiloJoules");
        unitsList.add("Calories");
        unitsList.add("KiloCalories");
        unitsList.add("Watts");
        unitsList.add("KiloWatts");
        unitsList.add("ElectronVolts");

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
                solutionView.setText("0");
                return;
            }

            convert();
            solutionView.setText(solution);
        });
    }

    private void initWidgets() {
        actionBar = getSupportActionBar();
        parentView = findViewById(R.id.parentView);
        firstUnitSP = findViewById(R.id.energyConverter1SP);
        secondUnitSP = findViewById(R.id.energyConverter2SP);
        backBtn = findViewById(R.id.energyConverterBackBtn);
        solveBtn = findViewById(R.id.energyConverterSolveBtn);
        solutionView = findViewById(R.id.energyConverterOutput);
        input = findViewById(R.id.energyConverterInput);
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
    private void convert() {
        double value = Double.parseDouble(input.getText().toString());

        if (firstUnitSP.getSelectedItem().equals(secondUnitSP.getSelectedItem()) || secondUnitSP.getSelectedItem().equals(firstUnitSP.getSelectedItem())) {
            solution = "" + value;
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(KILOWATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            solution = convertToJoules(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(KILOWATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            solution = convertToKiloJoules(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(KILOWATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            solution = convertToCalories(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToKiloCalories(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToKiloCalories(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToKiloCalories(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToKiloCalories(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToKiloCalories(KILOWATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            solution = convertToCalories(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(KILOWATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            solution = convertToWatt(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(6)) && secondUnitSP.getSelectedItem().equals(unitsList.get(5))) {
            solution = convertToKiloWatt(ELECTRONVOLT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(JOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(KILOJOULES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(CALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(KILOCALORIES, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(WATT, value);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(5)) && secondUnitSP.getSelectedItem().equals(unitsList.get(6))) {
            solution = convertToElectronVolts(KILOWATT, value);
        }
    }

}