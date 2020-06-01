package com.example.android_sep4.repositories;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.preference.PreferenceManager;

import com.example.android_sep4.R;
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
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
                    boolean prefTemperature = sharedPreferences.getBoolean(application.getString(R.string.pref_temperature_key), application.getResources().getBoolean(R.bool.pref_temperature_default));
                    for (Room room : rooms) {
                        if (!prefTemperature) {

                            double celsius = room.getTemperature();
                            double fahrenheit = celsius * 1.8 + 32.0;
                     /*    double celsiusLive = room.getLiveRoomMeasurements().getTemp();
                           double fahrenheitLive = celsiusLive* 1.8 + 32.0;*/
                            //  room.getLiveRoomMeasurements().setTemp(fahrenheitLive);
                            room.setTemperature(fahrenheit);
                        }

                    }
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
