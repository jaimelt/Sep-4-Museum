package com.example.android_sep4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

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
    @SerializedName("roomCode")
    @Expose
    private String roomCode;

    @ColumnInfo(name = "artwork_position")
    @SerializedName("artworkPosition")
    @Expose
    private int artworkPosition;

    @Ignore
    @SerializedName("artworkMeasurements")
    @Expose
    private ArtworkMeasurements artworkMeasurements;

    public Artwork() {

    }

    public Artwork(ArtworkMeasurements artworkMeasurements, String name, String description, String comment, String image, String type, String author, String roomCode) {
        this.artworkMeasurements = artworkMeasurements;
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.image = image;
        this.type = type;
        this.author = author;
        this.roomCode = roomCode;
    }

    protected Artwork(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
        type = in.readString();
        author = in.readString();
        roomCode = in.readString();
        artworkPosition = in.readInt();
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

    public void setId(int ID) {
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

    public ArtworkMeasurements getArtworkMeasurements() {
        return artworkMeasurements;
    }

    public void setArtworkMeasurements(int maxLight, int minLight, int maxTemp, int minTemp, int maxHum, int minHum, int maxCO2, int minCO2) {
        artworkMeasurements = new ArtworkMeasurements(maxLight, minLight, maxTemp, minTemp, maxHum, minHum, maxCO2, minCO2);
    }

    public void setArtworkMeasurements(ArtworkMeasurements artworkMeasurements) {
        this.artworkMeasurements = artworkMeasurements;
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
