package com.example.android_sep4.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.requests.clients.RoomsAPIClient;

import java.util.ArrayList;

public class RoomRepository {
    private static RoomRepository instance;
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksInRoomDataSet = new ArrayList<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private RoomsAPIClient roomsAPIClient;

    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Room>> getRoomsData() {
        return roomsAPIClient.getRoomsData();
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomIdData(String roomCode) {
        return roomsAPIClient.getArtworksByRoomIdData(roomCode);
    }

    public LiveData<Room> getRoomById(String id) {
        return roomsAPIClient.getRoomById(id);
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomsDataSet.get(position).setOptimalMeasurementConditions(humidity, temperature, co2, light);
    }

}
