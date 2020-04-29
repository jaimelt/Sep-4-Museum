package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.OptimalConditions;
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
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(30, 30, 1422, 40), "A1", "good room", 10, 6));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(50, 20, 5990, 40), "A2", "bad room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(10, 15, 5990, 40), "A3", "excellent room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(60, 33, 5990, 40), "B1", "worst room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(80, 34, 5990, 40), "B2", "goddest room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(90, 99, 5990, 40), "B3", "sex room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new OptimalConditions(30, 35, 5990, 40), "B4", "play room", 10, 5));
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }
}
