package com.example.android_sep4.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    private int light;
    private int temp;
    private int humidity;
    private int co2;

    public RoomMeasurements(int light, int temp, int humidity, int co2) {
        this.light = light;
        this.temp = temp;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public RoomMeasurements(String locationCode, int light, int temp, int humidity, int co2) {
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

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
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