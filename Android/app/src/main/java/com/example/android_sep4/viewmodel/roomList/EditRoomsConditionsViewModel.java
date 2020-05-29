package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.repositories.RoomRepository;

public class EditRoomsConditionsViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;
    private Room room;
    private int position;

    public EditRoomsConditionsViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCo2() {
        return room.getCo2();
    }

    public int getHumidity() {
        return room.getHumidity();
    }

    public double getLight() {
        return room.getLight();
    }

    public int getTemperature() {
        return room.getTemperature();
    }

    public void editRoomOptimal(String locationCode, String description, int totalCapacity, int currentCapacity,  int light, int co2,
                                int temperature, int humidity, int liveTemperature, int liveCo2, int liveHumidity, int liveLight) {
        RoomMeasurements roomMeasurements = new RoomMeasurements(liveLight, liveTemperature, liveHumidity, liveCo2);
        Room room = new Room(locationCode, description, totalCapacity, currentCapacity, null, light, temperature, humidity, co2, roomMeasurements);
        roomRepository.editRoomOptimal(room);
    }

}
