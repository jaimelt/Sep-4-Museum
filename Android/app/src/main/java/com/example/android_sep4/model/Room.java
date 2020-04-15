package com.example.android_sep4.model;

import java.util.ArrayList;

public class Room {
    private String locationCode;
    private String description;
    private String roomType;
    private int currentCapacity;
    private int totalCapacity;
    private ArrayList<Artwork> artworkList;
    private Measurements measurements;
    private boolean expanded;

    public Room(ArrayList<Artwork> artworkList, Measurements measurements, String locationCode, String description, String roomType, int totalCapacity, int currentCapacity)
    {
        this.locationCode = locationCode;
        this.description = description;
        this.roomType = roomType;
        this.totalCapacity = totalCapacity;
        this.currentCapacity = currentCapacity;
        //TODO: currentCapacity = 0 ?
        this.expanded = false;

        this.artworkList = artworkList;
        this.measurements = measurements;
    }

    public boolean isExpanded(){return expanded;}

    public void setExpanded( boolean expanded){this.expanded = expanded;}

    public void addArtwork(Artwork artwork)
    {
        artworkList.add(artwork);
    }

    public void deleteArtwork(Artwork artwork)
    {
        artworkList.remove(artwork);
    }

    public void deleteArtworkByID(String ID)
    {
        for(Artwork artwork:artworkList)
        {
            if(artwork.getID().equals(ID))
            {
                artworkList.remove(artwork);
            }
        }
    }

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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
