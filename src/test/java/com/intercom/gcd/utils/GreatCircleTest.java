package com.intercom.gcd.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreatCircleTest {

    @Test
    public void getDistanceInKmBetweenBigBenAndEiffelTower() {
        double bigBenLat = 51.5007;
        double bigBenLon = 0.1246;

        double eiffelLat = 48.8584;
        double eiffelLon = 2.2945;

        double expectedDistanceInKm = 332.0;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,eiffelLat,eiffelLon),0.1);
    }

    @Test
    public void getDistanceInKmBetweenBigBenAndDowning10() {
        double bigBenLat = 51.5007;
        double bigBenLon = 0.1246;

        double downing10lLat = 51.5034;
        double downing10Lon = 0.1276;

        double expectedDistanceInKm = 0.36;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,downing10lLat,downing10Lon),0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLat1() {
        double bigBenLat = 100;
        double bigBenLon = 0.1246;

        double downing10lLat = 51.5034;
        double downing10Lon = 0.1276;

        double expectedDistanceInKm = 0.36;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,downing10lLat,downing10Lon),0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLat2() {
        double bigBenLat = 51.5007;
        double bigBenLon = 0.1246;

        double downing10lLat =-95;
        double downing10Lon = 0.1276;

        double expectedDistanceInKm = 0.36;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,downing10lLat,downing10Lon),0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLon1() {
        double bigBenLat = 51.5007;
        double bigBenLon = 191;

        double downing10lLat = 51.5034;
        double downing10Lon = 0.1276;

        double expectedDistanceInKm = 0.36;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,downing10lLat,downing10Lon),0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadLon2() {
        double bigBenLat = 51.5007;
        double bigBenLon = 0.1246;

        double downing10lLat = 51.5034;
        double downing10Lon = -191;

        double expectedDistanceInKm = 0.36;

        assertEquals(expectedDistanceInKm,GreatCircle.getDistanceInKm(bigBenLat,bigBenLon,downing10lLat,downing10Lon),0.1);
    }
}