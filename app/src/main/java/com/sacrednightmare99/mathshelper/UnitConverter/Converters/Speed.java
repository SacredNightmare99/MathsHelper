package com.sacrednightmare99.mathshelper.UnitConverter.Converters;

public class Speed {
    public static final String SAME_UNITS = "SAME_UNITS";
    public static final String MPS_TO_KPH = "MPS_TO_KPH";
    public static final String MPS_TO_FPS = "MPS_TO_FPS";
    public static final String MPS_TO_MPH = "MPS_TO_MPH";
    public static final String MPS_TO_KNOT = "MPS_TO_KNOT";
    public static final String KPH_TO_MPS = "KPH_TO_MPS";
    public static final String KPH_TO_FPS = "KPH_TO_FPS";
    public static final String KPH_TO_MPH = "KPH_TO_MPH";
    public static final String KPH_TO_KNOT = "KPH_TO_KNOT";
    public static final String FPS_TO_MPS = "FPS_MPS";
    public static final String FPS_TO_KPH = "FPS_TO_KPH";
    public static final String FPS_TO_MPH = "FPS_TO_MPH";
    public static final String FPS_TO_KNOT = "FPS_TO_KNOT";
    public static final String MPH_TO_MPS = "MPH_TO_MPS";
    public static final String MPH_TO_KPH = "MPH_TO_KPH";
    public static final String MPH_TO_FPS = "MPH_TO_FPS";
    public static final String MPH_TO_KNOT = "MPH_TO_KNOT";
    public static final String KNOT_TO_MPS = "KNOT_TO_MPS";
    public static final String KNOT_TO_KPH = "KNOT_TO_KPH";
    public static final String KNOT_TO_FPS = "KNOT_TO_FPS";
    public static final String KNOT_TO_MPH = "KNOT_TO_MPH";

    private String currentConversion;

    public String getCurrentConversion() {
        return currentConversion;
    }

    public void setCurrentConversion(String currentConversion) {
        this.currentConversion = currentConversion;
    }
}
