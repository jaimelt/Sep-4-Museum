package com.example.android_sep4.model;

import com.google.gson.annotations.SerializedName;

public class Artwork {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("type")
    private String type;

    @SerializedName("author")
    private String author;

    @SerializedName("location")
    private String location;

    @SerializedName("artworkMeasurements")
    private ArtworkMeasurements artworkMeasurements;

    public Artwork(ArtworkMeasurements artworkMeasurements, String name, String description, String image, String type, String author, String location)
    {
        this.artworkMeasurements = artworkMeasurements;
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.author = author;
        this.location = location;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
