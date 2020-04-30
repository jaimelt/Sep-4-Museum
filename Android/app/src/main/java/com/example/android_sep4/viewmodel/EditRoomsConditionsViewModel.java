package com.example.android_sep4.viewmodel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.RoomRepository;

public class EditRoomsConditionsViewModel extends ViewModel {
    private RoomRepository roomRepository;
    private Room room;

    public void init(int position) {
        roomRepository = RoomRepository.getInstance();
        room = roomRepository.getRoom(position);
    }

    public int getCo2()
    {
        return room.getOptimalMeasurementConditions().getCo2();
    }

    public int getHumidity()
    {
        return room.getOptimalMeasurementConditions().getHumidity();
    }

    public int getLight()
    {
        return room.getOptimalMeasurementConditions().getLight();
    }

    public int getTemperature()
    {
        return room.getOptimalMeasurementConditions().getTemp();
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomRepository.editRoomOptimal(light,co2,temperature,humidity, position);
    }


}
