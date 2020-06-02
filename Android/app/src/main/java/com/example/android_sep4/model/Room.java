package com.example.android_sep4.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Room {
    @PrimaryKey
    @ColumnInfo(name = "location_code")
    @NonNull
    private String locationCode;
    private String description;
    @ColumnInfo(name = "total_capacity")
    private int totalCapacity;

    @Ignore
    private int currentCapacity;
    @Ignore
    private Artworks artworkList;
    @Ignore
    private double light;
    private double temperature;
    private double humidity;
    private double co2;
    @Ignore
    private RoomMeasurements liveRoomMeasurements;
    @Ignore
    private boolean expanded;

    public Room(@NonNull String locationCode, String description, int totalCapacity, int currentCapacity, Artworks artworkList, double light, double temperature, double humidity, double co2, RoomMeasurements liveRoomMeasurements) {
        this.locationCode = locationCode;
        this.description = description;
        this.totalCapacity = totalCapacity;
        this.currentCapacity = currentCapacity;
        this.artworkList = artworkList;
        this.light = light;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.liveRoomMeasurements = liveRoomMeasurements;
    }

    public Room() {
    }

    @NonNull
    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(@NonNull String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Artworks getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(Artworks artworkList) {
        this.artworkList = artworkList;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public RoomMeasurements getLiveRoomMeasurements() {
        return liveRoomMeasurements;
    }

    public void setLiveRoomMeasurements(RoomMeasurements liveRoomMeasurements) {
        this.liveRoomMeasurements = liveRoomMeasurements;
    }

    public boolean isExpanded() {
        return expanded;
    }



    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void changeCelsiusToFahrenheit() {
        double fahrenheit = (temperature * 1.8) + 32.0;
        this.setTemperature(fahrenheit);
    }
}
