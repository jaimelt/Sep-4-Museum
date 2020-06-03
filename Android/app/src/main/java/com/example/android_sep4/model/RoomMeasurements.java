package com.example.android_sep4.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Room.class,
                parentColumns = "location_code",
                childColumns = "id"
        )})
public class RoomMeasurements {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("light")
    @Expose
    private double light;
    @SerializedName("temperature")
    @Expose
    private double temperature;
    @SerializedName("humidity")
    @Expose
    private double humidity;
    @SerializedName("co2")
    @Expose
    private double co2;
    @SerializedName("measurementDate")
    @Expose
    private String measurementDate;
    @SerializedName("roomNo")
    @Expose
    private String roomNo;


    public RoomMeasurements(String locationCode, double light, double temp, double humidity, double co2) {
        this.roomNo = locationCode;
        this.light = light;
        this.temperature = temp;
        this.humidity = humidity;
        this.co2 = co2;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(String measurementDate) {
        this.measurementDate = measurementDate;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getTemp() {
        return temperature;
    }

    public void setTemp(double temp) {
        this.temperature = temp;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

}