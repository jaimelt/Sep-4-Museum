package com.example.android_sep4.model;

import java.util.ArrayList;

public class Rooms {
    private ArrayList<Room> rooms;

    public Rooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void changeCelsiusToFahrenheit() {
        for (Room room : rooms) {
            double celsius = room.getTemperature();
            double fahrenheit = celsius * 1.8 + 32.0;
            room.setTemperature(fahrenheit);
        }
    }
}
