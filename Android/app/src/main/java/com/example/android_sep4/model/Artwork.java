package com.example.android_sep4.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artwork {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("location")
    @Expose
    private String roomCode;

    @SerializedName("artworkPosition")
    @Expose
    private int artworkPosition;

    @SerializedName("maxLight")
    @Expose
    @Nullable
    private int maxLight;

    @SerializedName("minLight")
    @Expose
    @Nullable
    private int minLight;

    @SerializedName("maxTemperature")
    @Expose
    @Nullable
    private int maxTemperature;

    @SerializedName("minTemperature")
    @Expose
    @Nullable
    private int minTemperature;

    @SerializedName("maxHumidity")
    @Expose
    @Nullable
    private int maxHumidity;

    @SerializedName("minHumidity")
    @Expose
    @Nullable
    private int minHumidity;

    @SerializedName("maxCo2")
    @Expose
    @Nullable
    private int maxCo2;

    @SerializedName("minCo2")
    @Expose
    @Nullable
    private int minCo2;

    public Artwork() {

    }

    public Artwork(int id, String name, String description, String comment, String image, String type, String author, String roomCode, int artworkPosition, int maxLight, int minLight, int maxTemperature, int minTemperature, int maxHumidity, int minHumidity, int maxCo2, int minCo2) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.image = image;
        this.type = type;
        this.author = author;
        this.roomCode = roomCode;
        this.artworkPosition = artworkPosition;
        this.maxLight = maxLight;
        this.minLight = minLight;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.maxHumidity = maxHumidity;
        this.minHumidity = minHumidity;
        this.maxCo2 = maxCo2;
        this.minCo2 = minCo2;
    }

    //Constructor for Room database
    public Artwork(String name, String description, String comment, String image, String type, String author, String roomCode, int artworkPosition, int maxLight, int minLight, int maxTemperature, int minTemperature, int maxHumidity, int minHumidity, int maxCo2, int minCo2) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.image = image;
        this.type = type;
        this.author = author;
        this.roomCode = roomCode;
        this.artworkPosition = artworkPosition;
        this.maxLight = maxLight;
        this.minLight = minLight;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.maxHumidity = maxHumidity;
        this.minHumidity = minHumidity;
        this.maxCo2 = maxCo2;
        this.minCo2 = minCo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getArtworkPosition() {
        return artworkPosition;
    }

    public void setArtworkPosition(int artworkPosition) {
        this.artworkPosition = artworkPosition;
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

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
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

    public int getMaxCo2() {
        return maxCo2;
    }

    public void setMaxCo2(int maxCo2) {
        this.maxCo2 = maxCo2;
    }

    public int getMinCo2() {
        return minCo2;
    }

    public void setMinCo2(int minCo2) {
        this.minCo2 = minCo2;
    }

}
