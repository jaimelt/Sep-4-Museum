package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Measurements;
import com.example.android_sep4.model.Room;

import java.util.ArrayList;

public class RoomRepository {
    private static RoomRepository instance;
    private ArrayList<Room> roomsDataSet = new ArrayList<>();

    public static RoomRepository getInstance() {
        if(instance == null) {
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
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(300, 30, 1422, 40), "Room1", "good room", "Paintings", 10, 6));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(500, 20, 5990, 40), "Room2", "bad room", "Stone", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(100, 15, 5990, 40), "Room3", "excellent room", "Fresca", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(600, 33, 5990, 40), "Room4", "worst room", "Medieval", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(800, 34, 5990, 40), "Room5", "goddest room", "Modern", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(900, 99, 5990, 40), "Room6", "sex room", "Rustic", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(300, 35, 5990, 40), "Room7", "play room", "Peric", 10, 5));
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }
}
