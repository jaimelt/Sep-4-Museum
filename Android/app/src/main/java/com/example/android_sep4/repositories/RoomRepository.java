package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.requests.clients.RoomsAPIClient;

import java.util.ArrayList;

public class RoomRepository {
    private static RoomRepository instance;
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();

    private MutableLiveData<RoomMeasurements> liveMeasurements = new MutableLiveData<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private RoomsAPIClient roomsAPIClient;
    private Application application;
    private boolean danger;

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

    public LiveData<ArrayList<Room>> getRoomsData() {
        roomsAPIClient.getRoomsData();
        roomsAPIClient.getRoomsDataLive().observeForever(new Observer<ArrayList<Room>>() {
            @Override
            public void onChanged(ArrayList<Room> rooms) {
                if (rooms.isEmpty()) {
                } else {
                    roomsData.setValue(rooms);
                }
            }
        });
        return roomsData;
    }


/*    public LiveData<RoomMeasurements> getLiveMeasurements(String roomCode) {
        roomsAPIClient.getLiveMeasurements(roomCode);
        roomsAPIClient.getLiveMeasurements().observeForever(new Observer<RoomMeasurements>() {
            @Override
            public void onChanged(RoomMeasurements liveData) {
                if (liveData == null) {
                } else {
                    liveMeasurements.setValue(liveData);
                }
            }
        });
        return liveMeasurements;
    }*/

    public LiveData<Boolean> getIsLoading() {
        return roomsAPIClient.getIsLoading();
    }

    public void editRoomOptimal(Room room) {
        roomsAPIClient.editRoomOptimalConditions(room);
    }
}
