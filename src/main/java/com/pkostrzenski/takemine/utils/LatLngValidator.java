package com.pkostrzenski.takemine.utils;

public class LatLngValidator {

    private static final double MIN_LAT = -90;
    private static final double MAX_LAT = 90;
    private static final double MIN_LNG = -180;
    private static final double MAX_LNG = 180;

    public static boolean validateLatLng(double lat, double lng){
        return lat >= MIN_LAT && lat <= MAX_LAT && lng >= MIN_LNG && lng <= MAX_LNG;
    }
}
