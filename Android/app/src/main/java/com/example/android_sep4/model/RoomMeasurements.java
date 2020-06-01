package com.example.android_sep4.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Room.class,
                parentColumns = "location_code",
                childColumns = "id"
        )})
public class RoomMeasurements {
    @PrimaryKey
    @NonNull
    private String id;
    private double light;
    @SerializedName("temperature")
    private double temp;
    private double humidity;
    private double co2;

    public RoomMeasurements(double light, double temp, double humidity, double co2) {
        this.light = light;
        this.temp = temp;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public RoomMeasurements(String locationCode, double light, double temp, double humidity, double co2) {
        id = locationCode;
        this.light = light;
        this.temp = temp;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

}