package com.sacrednightmare99.mathshelper.UnitConverter.Converters;

import com.sacrednightmare99.mathshelper.ActivityClass;

import java.text.DecimalFormat;

public class Energy extends ActivityClass {

    public static final String JOULES = "JOULES";
    public static final String KILOJOULES = "KILOJOULES";
    public static final String CALORIES = "CALORIES";
    public static final String KILOCALORIES = "KILOCALORIES";
    public static final String WATT = "WATT";
    public static final String KILOWATT = "KILOWATT";
    public static final String ELECTRONVOLT = "ELECTRONVOLT";
    private final DecimalFormat dfE = new DecimalFormat("0.##E0");
    private final DecimalFormat df = new DecimalFormat("#.######");

    public String convertToJoules(String key, double value) {
        String x = "";

        switch (key) {
            case KILOJOULES:
                x = df.format(value*1000);
                break;
            case CALORIES:
                x = df.format(value*4.184);
                break;
            case KILOCALORIES:
                x = df.format(value*4184);
                break;
            case WATT:
                x = df.format(value*3600);
                break;
            case KILOWATT:
                x = dfE.format(value*3.6e+6);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/6.242e+18);
                break;
        }

        return x;
    }
    public String convertToKiloJoules(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = df.format(value/1000);
                break;
            case CALORIES:
                x = df.format(value/239);
                break;
            case KILOCALORIES:
                x = df.format(value*4.184);
                break;
            case WATT:
                x = df.format(value*3.6);
                break;
            case KILOWATT:
                x = df.format(value*3600);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/6.242e+21);
                break;
        }

        return x;
    }
    public String convertToCalories(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = df.format(value/4.184);
                break;
            case KILOJOULES:
                x = df.format(value*239);
                break;
            case KILOCALORIES:
                x = df.format(value*1000);
                break;
            case WATT:
                x = df.format(value*860.4);
                break;
            case KILOWATT:
                x = df.format(value*860400);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/2.611e+19);
                break;
        }

        return x;
    }
    public String convertToKiloCalories(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = df.format(value/4184);
                break;
            case KILOJOULES:
                x = df.format(value*4.184);
                break;
            case CALORIES:
                x = df.format(value/1000);
                break;
            case WATT:
                x = df.format(value/1.162);
                break;
            case KILOWATT:
                x = df.format(value*860.4);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/2.611e+22);
                break;
        }

        return x;
    }
    public String convertToWatt(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = df.format(value/3600);
                break;
            case KILOJOULES:
                x = df.format(value/3.6);
                break;
            case CALORIES:
                x = df.format(value/860.4);
                break;
            case KILOCALORIES:
                x = df.format(value*1.162);
                break;
            case KILOWATT:
                x = df.format(value*1000);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/2.247e+22);
                break;
        }

        return x;
    }
    public String convertToKiloWatt(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = dfE.format(value/3.6e+6);
                break;
            case KILOJOULES:
                x = df.format(value/3600);
                break;
            case CALORIES:
                x = df.format(value/860400);
                break;
            case KILOCALORIES:
                x = df.format(value/860.4);
                break;
            case WATT:
                x = df.format(value/1000);
                break;
            case ELECTRONVOLT:
                x = dfE.format(value/2.247e+25);
                break;
        }

        return x;
    }
    public String convertToElectronVolts(String key, double value) {
        String x = "";

        switch (key) {
            case JOULES:
                x = dfE.format(value*6.242e+18);
                break;
            case KILOJOULES:
                x = dfE.format(value*6.242e+21);
                break;
            case CALORIES:
                x = dfE.format(value*2.611e+19);
                break;
            case KILOCALORIES:
                x = dfE.format(value*2.611e+22);
                break;
            case WATT:
                x = dfE.format(value*2.247e+22);
                break;
            case KILOWATT:
                x = dfE.format(value*2.247e+25);
                break;
        }

        return x;
    }

}
