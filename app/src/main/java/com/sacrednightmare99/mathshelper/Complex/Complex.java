package com.sacrednightmare99.mathshelper.Complex;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public class Complex {

    private static DecimalFormat df = new DecimalFormat("#.##");
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
        int a = (int) Math.sqrt(real*real + image*image);
        if (a * a == real * real + image * image) {
            return df.format(a) + "";
        } else {
            int b = (int) Math.sqrt(real * real + image * image);
            return df.format(b) + "√" + df.format((real * real + image * image) / (b * b));
        }
    }

    public String argument() {
        double arg = Math.atan2(image, real);
        String argStr = df.format(arg);
        if (argStr.equals("0.00")) {
            return "0";
        } else if (argStr.equals("0.79") || argStr.equals("0.80")) {
            return "π/4";
        } else if (argStr.equals("0.52") || argStr.equals("0.53")) {
            return "π/6";
        } else if (argStr.equals("1.05") || argStr.equals("1.06")) {
            return "π/3";
        } else if (argStr.equals("1.57") || argStr.equals("1.58")) {
            return "π/2";
        } else if (argStr.equals("2.09") || argStr.equals("2.10")) {
            return "2π/3";
        } else if (argStr.equals("2.35") || argStr.equals("2.36")) {
            return "3π/4";
        } else if (argStr.equals("2.57") || argStr.equals("2.58")) {
            return "5π/6";
        } else if (argStr.equals("3.14") || argStr.equals("3.15")) {
            return "π";
        } else if (argStr.equals("-0.79") || argStr.equals("-0.80")) {
            return "-π/4";
        } else if (argStr.equals("-0.52") || argStr.equals("-0.53")) {
            return "-π/6";
        } else if (argStr.equals("-1.05") || argStr.equals("-1.06")) {
            return "-π/3";
        } else if (argStr.equals("-1.57") || argStr.equals("-1.58")) {
            return "-π/2";
        } else if (argStr.equals("-2.09") || argStr.equals("-2.10")) {
            return "-2π/3";
        } else if (argStr.equals("-2.35") || argStr.equals("-2.36")) {
            return "-3π/4";
        } else if (argStr.equals("-2.57") || argStr.equals("-2.58")) {
            return "-5π/6";
        } else if (argStr.equals("-3.14") || argStr.equals("-3.15")) {
            return "-π";
        } else {
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
