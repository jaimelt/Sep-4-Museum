package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;

import java.util.ArrayList;

public class RoomRepository {
    private static RoomRepository instance;
    private ArrayList<Room> roomsDataSet = new ArrayList<>();

    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
    public MutableLiveData<ArrayList<Room>> getRoomsData() {
        setRooms();

        MutableLiveData<ArrayList<Room>> data = new MutableLiveData<>();
        data.setValue(roomsDataSet);
        return data;
    }

    private void setRooms() {
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(3000, 30, 14, 400), new RoomMeasurements(5000, 30, 14, 400), "A1", "good room", 10, 6));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(5000, 20, 90, 400), new RoomMeasurements(5000, 30, 22, 400), "A2", "bad room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(1000, 15, 59, 400), new RoomMeasurements(5000, 30, 12, 400), "A3", "excellent room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(6000, 33, 50, 400), new RoomMeasurements(5000, 30, 22, 400), "B1", "worst room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(8000, 34, 90, 400), new RoomMeasurements(5000, 30, 14, 400), "B2", "goddest room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(9000, 99, 59, 400), new RoomMeasurements(5000, 30, 12, 400), "B3", "sex room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(3000, 35, 90, 400), new RoomMeasurements(5000, 30, 22, 400), "B4", "play room", 10, 5));
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomsDataSet.get(position).setOptimalMeasurementConditions(humidity, temperature, co2, light);
    }

}
