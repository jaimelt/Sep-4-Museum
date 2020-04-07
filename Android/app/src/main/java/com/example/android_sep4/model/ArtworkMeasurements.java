package com.example.android_sep4.model;

public class ArtworkMeasurements {
    private int maxLight;
    private int minLight;
    private int maxTemp;
    private int minTemp;
    private int maxHumidity;
    private int minHumidity;
    private int maxCO2;
    private int minCO2;

    public ArtworkMeasurements(int maxLight, int minLight, int maxTemp, int minTemp, int maxHumidity, int minHumidity, int maxCO2, int minCO2)
    {
        this.maxLight = maxLight;
        this.minLight = minLight;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.maxHumidity = maxHumidity;
        this.minHumidity = minHumidity;
        this.maxCO2 = maxCO2;
        this.minCO2 = minCO2;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }

    public int getMinLight() {
        return minLight;
    }

    public void setMinLight(int minLight) {
        this.minLight = minLight;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(int maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(int minHumidity) {
        this.minHumidity = minHumidity;
    }

    public int getMaxCO2() {
        return maxCO2;
    }

    public void setMaxCO2(int maxCO2) {
        this.maxCO2 = maxCO2;
    }

    public int getMinCO2() {
        return minCO2;
    }

    public void setMinCO2(int minCO2) {
        this.minCO2 = minCO2;
    }
}
