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
    private ArrayList<Artwork> artworkList;
    @Ignore
    private int light;
    private int temperature;
    private int humidity;
    private int co2;
    @Ignore
    private RoomMeasurements liveRoomMeasurements;
    @Ignore
    private boolean expanded;

    public Room(@NonNull String locationCode, String description, int totalCapacity, int currentCapacity, ArrayList<Artwork> artworkList, int light, int temperature, int humidity, int co2, RoomMeasurements liveRoomMeasurements) {
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

    public ArrayList<Artwork> getArtworkList() {
        return artworkList;
    }

    public void setArtworkList(ArrayList<Artwork> artworkList) {
        this.artworkList = artworkList;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
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
}
