package com.example.android_sep4.model;

import java.util.ArrayList;

public class Room {
    private String locationCode;
    private String description;
    private int currentCapacity;
    private int totalCapacity;
    private ArrayList<Artwork> artworkList;
    private OptimalConditions optimalConditions;
    private boolean expanded;

    public Room(ArrayList<Artwork> artworkList, OptimalConditions optimalConditions, String locationCode, String description, int totalCapacity, int currentCapacity)
    {
        this.locationCode = locationCode;
        this.description = description;
        this.totalCapacity = totalCapacity;
        this.currentCapacity = currentCapacity;
        this.expanded = false;
        this.artworkList = artworkList;
        this.optimalConditions = optimalConditions;
    }

    public OptimalConditions getOptimalConditions() {
        return optimalConditions;
    }

    public void setOptimalConditions(OptimalConditions optimalConditions) {
        this.optimalConditions = optimalConditions;
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
