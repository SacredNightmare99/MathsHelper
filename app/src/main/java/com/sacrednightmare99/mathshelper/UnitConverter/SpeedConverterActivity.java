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
import com.sacrednightmare99.mathshelper.UnitConverter.Converters.Speed;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SpeedConverterActivity extends AppCompatActivity {

    private UserSettings userSettings;
    private ActionBar actionBar;
    private View parentView;
    private Spinner firstUnitSP, secondUnitSP;
    private Button backBtn, solveBtn;
    private TextView solutionView;
    private EditText input;
    private String solution;
    private Speed speed;
    private ArrayList<String> unitsList;
    private final DecimalFormat df = new DecimalFormat("#.#####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_converter);

        userSettings = (UserSettings) getApplication();
        initWidgets();
        loadSharedPreferences();

        backBtn.setOnClickListener(View -> finish());

        unitsList = new ArrayList<>();
        unitsList.add("Meter per second");
        unitsList.add("Kilometer per hour");
        unitsList.add("Foot per second");
        unitsList.add("Miles per hour");
        unitsList.add("Knot");

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
        actionBar = getSupportActionBar();
        parentView = findViewById(R.id.parentView);
        firstUnitSP = findViewById(R.id.speedConverter1SP);
        secondUnitSP = findViewById(R.id.speedConverter2SP);
        backBtn = findViewById(R.id.speedConverterBackBtn);
        solveBtn = findViewById(R.id.speedConverterSolveBtn);
        solutionView = findViewById(R.id.speedConverterOutput);
        input = findViewById(R.id.speedConverterInput);
        speed = new Speed();
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

    private void checkWhatToConvert() {
        if (firstUnitSP.getSelectedItem().equals(secondUnitSP.getSelectedItem()) || secondUnitSP.getSelectedItem().equals(firstUnitSP.getSelectedItem())) {
            speed.setCurrentConversion(Speed.SAME_UNITS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            speed.setCurrentConversion(Speed.MPS_TO_KPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            speed.setCurrentConversion(Speed.MPS_TO_FPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            speed.setCurrentConversion(Speed.MPS_TO_MPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(0)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            speed.setCurrentConversion(Speed.MPS_TO_KNOT);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            speed.setCurrentConversion(Speed.KPH_TO_MPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            speed.setCurrentConversion(Speed.KPH_TO_FPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            speed.setCurrentConversion(Speed.KPH_TO_MPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(1)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            speed.setCurrentConversion(Speed.KPH_TO_KNOT);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            speed.setCurrentConversion(Speed.FPS_TO_MPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            speed.setCurrentConversion(Speed.FPS_TO_KPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            speed.setCurrentConversion(Speed.FPS_TO_MPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(2)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            speed.setCurrentConversion(Speed.FPS_TO_KNOT);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            speed.setCurrentConversion(Speed.MPH_TO_MPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            speed.setCurrentConversion(Speed.MPH_TO_KPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            speed.setCurrentConversion(Speed.MPH_TO_FPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(3)) && secondUnitSP.getSelectedItem().equals(unitsList.get(4))) {
            speed.setCurrentConversion(Speed.MPH_TO_KNOT);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(0))) {
            speed.setCurrentConversion(Speed.KNOT_TO_MPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(1))) {
            speed.setCurrentConversion(Speed.KNOT_TO_KPH);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(2))) {
            speed.setCurrentConversion(Speed.KNOT_TO_FPS);
        } else if (firstUnitSP.getSelectedItem().equals(unitsList.get(4)) && secondUnitSP.getSelectedItem().equals(unitsList.get(3))) {
            speed.setCurrentConversion(Speed.KNOT_TO_MPH);
        }
    }

    private void solve() {
        checkWhatToConvert();

        switch (speed.getCurrentConversion()) {
            case Speed.SAME_UNITS:
                convertSame();
                break;
            case Speed.MPS_TO_KPH:
                MPS_TO_KPH();
                break;
            case Speed.MPS_TO_FPS:
                MPS_TO_FPS();
                break;
            case Speed.MPS_TO_MPH:
                MPS_TO_MPH();
                break;
            case Speed.MPS_TO_KNOT:
                MPS_TO_KNOT();
                break;
            case Speed.KPH_TO_MPS:
                KPH_TO_MPS();
                break;
            case Speed.KPH_TO_FPS:
                KPH_TO_FPS();
                break;
            case Speed.KPH_TO_MPH:
                KPH_TO_MPH();
                break;
            case Speed.KPH_TO_KNOT:
                KPH_TO_KNOT();
                break;
            case Speed.FPS_TO_MPS:
                FPS_TO_MPS();
                break;
            case Speed.FPS_TO_KPH:
                FPS_TO_KPH();
                break;
            case Speed.FPS_TO_MPH:
                FPS_TO_MPH();
                break;
            case Speed.FPS_TO_KNOT:
                FPS_TO_KNOT();
                break;
            case Speed.MPH_TO_MPS:
                MPH_TO_MPS();
                break;
            case Speed.MPH_TO_KPH:
                MPH_TO_KPH();
                break;
            case Speed.MPH_TO_FPS:
                MPH_TO_FPS();
                break;
            case Speed.MPH_TO_KNOT:
                MPH_TO_KNOT();
                break;
            case Speed.KNOT_TO_MPS:
                KNOT_TO_MPS();
                break;
            case Speed.KNOT_TO_KPH:
                KNOT_TO_KPH();
                break;
            case Speed.KNOT_TO_FPS:
                KNOT_TO_FPS();
                break;
            case Speed.KNOT_TO_MPH:
                KNOT_TO_MPH();
                break;
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

    private void convertSame() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x);
    }
    private void MPS_TO_KPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*3.6);
    }
    private void MPS_TO_FPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*3.281);
    }
    private void MPS_TO_MPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*2.237);
    }
    private void MPS_TO_KNOT() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.944);
    }
    private void KPH_TO_MPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/3.6);
    }
    private void KPH_TO_FPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.097);
    }
    private void KPH_TO_MPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.609);
    }
    private void KPH_TO_KNOT() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.852);
    }
    private void FPS_TO_MPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/3.281);
    }
    private void FPS_TO_KPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.097);
    }
    private void FPS_TO_MPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.467);
    }
    private void FPS_TO_KNOT() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.688);
    }
    private void MPH_TO_MPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/2.237);
    }
    private void MPH_TO_KPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.609);
    }
    private void MPH_TO_FPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.467);
    }
    private void MPH_TO_KNOT() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.151);
    }
    private void KNOT_TO_MPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x/1.944);
    }
    private void KNOT_TO_KPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.852);
    }
    private void KNOT_TO_FPS() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.688);
    }
    private void KNOT_TO_MPH() {
        double x = Double.parseDouble(input.getText().toString());
        solution = "" + df.format(x*1.151);
    }

}