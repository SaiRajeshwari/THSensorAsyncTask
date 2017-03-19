package com.example.rajee.thsensorasynctask;

/**
 * Created by rajee on 3/19/17.
 */

public class SensorReading {
    String rOutput = "OUTPUT ";
    String rTemperature;
    String rHumidity;
    String rActivity;

    SensorReading(String output, String temperature, String humidity, String activity){
        rOutput = rOutput + output + ":";
        rTemperature = temperature;
        rHumidity = humidity;
        rActivity = activity;
    }

    @Override
    public String toString() {
        return rOutput + "\n" +
                "Temperature: " + rTemperature + " F" + "\n" +
                "Humidity:  " + rHumidity + " %" + "\n" +
                "Activity:  " + rActivity + "\n";
    }
}
