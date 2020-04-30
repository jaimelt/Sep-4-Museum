package com.example.android_sep4.model;

import java.util.ArrayList;

public class Room {
    private String locationCode;
    private String description;
    private int currentCapacity;
    private int totalCapacity;
    private ArrayList<Artwork> artworkList;
    private RoomMeasurements optimalMeasurementConditions;
    private RoomMeasurements measurementConditions;
    private boolean expanded;

    public Room(ArrayList<Artwork> artworkList, RoomMeasurements optimalMeasurementConditions, RoomMeasurements measurementConditions, String locationCode, String description, int totalCapacity, int currentCapacity)
    {
        this.locationCode = locationCode;
        this.description = description;
        this.totalCapacity = totalCapacity;
        this.currentCapacity = currentCapacity;
        this.expanded = false;
        this.artworkList = artworkList;
        this.optimalMeasurementConditions = optimalMeasurementConditions;
        this.measurementConditions =measurementConditions;
    }

    public RoomMeasurements getMeasurementConditions() {
        return measurementConditions;
    }

    public void setMeasurementConditions(RoomMeasurements measurementConditions) {
        this.measurementConditions = measurementConditions;
    }

    public RoomMeasurements getOptimalMeasurementConditions() {
        return optimalMeasurementConditions;
    }

    public void setOptimalMeasurementConditions(int humidity, int temp, int co2, int light) {
        this.optimalMeasurementConditions = new RoomMeasurements(light,temp,humidity,co2);
    }
    public boolean isExpanded(){return expanded;}

    public void setExpanded( boolean expanded){this.expanded = expanded;}

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
