package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.requests.clients.RoomsAPIClient;


public class RoomRepository {
    private static RoomRepository instance;
    private RoomsAPIClient roomsAPIClient;
    private Application application;

    public RoomRepository(Application application) {
        this.application = application;
        roomsAPIClient = new RoomsAPIClient(application);
    }

    public static RoomRepository getInstance(Application application) {
        if (instance == null) {
            instance = new RoomRepository(application);
        }
        return instance;
    }

    public void getRoomsData() {
        roomsAPIClient.getRoomsData();
    }

    public LiveData<Rooms> getRoomsDataLive() {
        return roomsAPIClient.getRoomsDataLive();
    }

    public LiveData<Boolean> getIsLoading() {
        return roomsAPIClient.getIsLoading();
    }

    public void editRoomOptimal(Room room) {
        roomsAPIClient.editRoomOptimalConditions(room);
    }
}
