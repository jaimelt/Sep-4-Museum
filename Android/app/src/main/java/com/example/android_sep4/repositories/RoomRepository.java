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
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room1", "good room", "Paintings", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room2", "bad room", "Stone", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room3", "excellent room", "Fresca", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room4", "worst room", "Medieval", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room5", "goddest room", "Modern", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room6", "sex room", "Rustic", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new Measurements(), "Room7", "play room", "Peric", 10, 5));
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }
}
