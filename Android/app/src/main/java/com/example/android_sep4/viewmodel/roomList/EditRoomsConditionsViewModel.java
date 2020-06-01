package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.RoomRepository;

public class EditRoomsConditionsViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;
    private Room room;
    private int position;

    public EditRoomsConditionsViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
        room = roomRepository.getRoom(position);
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

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomRepository.editRoomOptimal(light, co2, temperature, humidity, position);
    }


}
