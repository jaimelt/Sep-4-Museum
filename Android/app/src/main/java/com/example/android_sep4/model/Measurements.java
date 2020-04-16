package com.example.android_sep4.model;

public class Measurements {
    private int co2;
    private int temperature;
    private int light;
    private int humidity;

    public Measurements(int co2, int temperature, int light, int humidity) {
        this.co2 = co2;
        this.humidity = humidity;
        this.light = light;
        this.temperature = temperature;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }



}