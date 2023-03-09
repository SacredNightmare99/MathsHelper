package com.sacrednightmare99.mathshelper.Complex;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public class Complex {

    private final DecimalFormat df = new DecimalFormat("#.##");
    private final double real;
    private final double image;

    public Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    public Complex conjugate() {
        return new Complex(real, -image);
    }

    public String modulus() {
        double sqrt = Math.sqrt(real * real + image * image);
        int a = (int) sqrt;
        if (a * a == real * real + image * image) {
            return df.format(a) + "";
        } else {
            int b = (int) sqrt;
            return df.format(b) + "√" + df.format((real * real + image * image) / (b * b));
        }
    }

    public String argument() {
        double arg = Math.atan2(image, real);
        String argStr = df.format(arg);
        switch (argStr) {
            case "0.00":
                return "0";
            case "0.79":
            case "0.80":
                return "π/4";
            case "0.52":
            case "0.53":
                return "π/6";
            case "1.05":
            case "1.06":
                return "π/3";
            case "1.57":
            case "1.58":
                return "π/2";
            case "2.09":
            case "2.10":
                return "2π/3";
            case "2.35":
            case "2.36":
                return "3π/4";
            case "2.57":
            case "2.58":
                return "5π/6";
            case "3.14":
            case "3.15":
                return "π";
            case "-0.79":
            case "-0.80":
                return "-π/4";
            case "-0.52":
            case "-0.53":
                return "-π/6";
            case "-1.05":
            case "-1.06":
                return "-π/3";
            case "-1.57":
            case "-1.58":
                return "-π/2";
            case "-2.09":
            case "-2.10":
                return "-2π/3";
            case "-2.35":
            case "-2.36":
                return "-3π/4";
            case "-2.57":
            case "-2.58":
                return "-5π/6";
            case "-3.14":
            case "-3.15":
                return "-π";
            default:
                return df.format(Math.toDegrees(arg)) + "°";
        }
    }

    @NonNull
    public String toString() {
        if (image < 0) {
            return df.format(real) + " " + df.format(image) + "i";
        } else if (image == 0) {
            return df.format(real);
        } else {
            return df.format(real) + " + " + df.format(image) + "i";
        }
    }

    public String toPolarString() {
        String arg = argument();
        return modulus() + "(cos(" + arg + ") + i sin(" + arg + "))";
    }

    public String toEulerString() {
        String arg = argument();
        return modulus() + " * e^(i " + arg + ")";
    }

}
