package com.example.android_sep4.model;

public class Artwork {
    private String ID;
    private String name;
    private String description;
    private String image;
    private String type;
    private String author;
    private ArtworkMeasurements artworkMeasurements;

    public Artwork(ArtworkMeasurements artworkMeasurements, String name, String description, String image, String type, String author)
    {
        this.artworkMeasurements = artworkMeasurements;
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.author = author;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
}
