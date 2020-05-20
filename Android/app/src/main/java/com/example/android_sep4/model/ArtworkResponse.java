package com.example.android_sep4.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtworkResponse {
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
    @SerializedName("maxLight")
    @Expose
    private int maxLight;
    @SerializedName("minLight")
    @Expose
    private int minLight;
    @SerializedName("maxTemperature")
    @Expose
    private int maxTemperature;
    @SerializedName("minTemperature")
    @Expose
    private int minTemperature;
    @SerializedName("maxHumidity")
    @Expose
    private int maxHumidity;
    @SerializedName("minHumidity")
    @Expose
    private int minHumidity;
    @SerializedName("maxCo2")
    @Expose
    private int maxCo2;
    @SerializedName("minCo2")
    @Expose
    private int minCo2;

    public ArtworkResponse(int id, String name, String description, String comment, String image, String type, String author, String roomCode, int maxLight, int minLight, int maxTemperature, int minTemperature, int maxHumidity, int minHumidity, int maxCo2, int minCo2) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.image = image;
        this.type = type;
        this.author = author;
        this.roomCode = roomCode;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public int getMinLight() {
        return minLight;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxHumidity() {
        return maxHumidity;
    }

    public int getMinHumidity() {
        return minHumidity;
    }

    public int getMaxCo2() {
        return maxCo2;
    }

    public int getMinCo2() {
        return minCo2;
    }
}

