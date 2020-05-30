package com.example.android_sep4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Room.class,
                parentColumns = "location_code",
                childColumns = "room_code"
        )})
public class Artwork implements Parcelable {

    @PrimaryKey(autoGenerate = true)
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

    @ColumnInfo(name = "room_code")
    @SerializedName("location")
    @Expose
    private String roomCode;

    @ColumnInfo(name = "artwork_position")
    @SerializedName("artworkPosition")
    @Expose
    private int artworkPosition;

    @ColumnInfo(name = "max_light")
    @SerializedName("maxLight")
    @Expose
    @Nullable
    private int maxLight;

    @ColumnInfo(name = "min_light")
    @SerializedName("minLight")
    @Expose
    @Nullable
    private int minLight;

    @ColumnInfo(name = "max_temperature")
    @SerializedName("maxTemperature")
    @Expose
    @Nullable
    private int maxTemperature;

    @ColumnInfo(name = "min_temperature")
    @SerializedName("minTemperature")
    @Expose
    @Nullable
    private int minTemperature;

    @ColumnInfo(name = "max_humidity")
    @SerializedName("maxHumidity")
    @Expose
    @Nullable
    private int maxHumidity;

    @ColumnInfo(name = "min_humidity")
    @SerializedName("minHumidity")
    @Expose
    @Nullable
    private int minHumidity;

    @ColumnInfo(name = "max_co2")
    @SerializedName("maxCo2")
    @Expose
    @Nullable
    private int maxCo2;

    @ColumnInfo(name = "min_co2")
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

    protected Artwork(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        comment = in.readString();
        image = in.readString();
        type = in.readString();
        author = in.readString();
        roomCode = in.readString();
        artworkPosition = in.readInt();
        maxLight = in.readInt();
        minLight = in.readInt();
        maxTemperature = in.readInt();
        minTemperature = in.readInt();
        maxHumidity = in.readInt();
        minHumidity = in.readInt();
        maxCo2 = in.readInt();
        minCo2 = in.readInt();
    }

    public static final Creator<Artwork> CREATOR = new Creator<Artwork>() {
        @Override
        public Artwork createFromParcel(Parcel in) {
            return new Artwork(in);
        }

        @Override
        public Artwork[] newArray(int size) {
            return new Artwork[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(type);
        parcel.writeString(author);
        parcel.writeString(roomCode);
        parcel.writeInt(artworkPosition);
    }
}
