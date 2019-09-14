package com.intercom.gcd.utils;

public class GreatCircle {

    // each degree on a great circle of Earth is 111.222568512 km
    public static double CIRCLE_DEGREE = 111.222568512;

    public static double getDistanceInKm(double lat1, double lon1,double lat2, double lon2) throws IllegalArgumentException {
        if(!isLatitudeValid(lat1) || !isLatitudeValid(lat2) || !isLongitudeValid(lon1) || !isLongitudeValid(lon2))
            throw new IllegalArgumentException("Invalid latitude or longitude");

        // convert to radians
        double lat1r = Math.toRadians(lat1);
        double lat2r = Math.toRadians(lat2);
        double lon1r = Math.toRadians(lon1);
        double lon2r = Math.toRadians(lon2);

        // do the spherical trig calculation
        double angle = Math.acos(Math.sin(lat1r) * Math.sin(lat2r)  +
                Math.cos(lat1r) * Math.cos(lat2r) * Math.cos(lon1r - lon2r));

        // convert back to degrees
        angle = Math.toDegrees(angle);

        return CIRCLE_DEGREE * angle;
    }

    private static boolean isLatitudeValid(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private static boolean isLongitudeValid(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }


}
