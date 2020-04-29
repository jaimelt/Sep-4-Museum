package com.example.android_sep4.viewmodel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Measurements;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.ArtworksRepository;
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
        return room.getOptimalMeasurements().getCo2();
    }

    public int getHumidity()
    {
        return room.getOptimalMeasurements().getHumidity();
    }

    public int getLight()
    {
        return room.getOptimalMeasurements().getLight();
    }

    public int getTemperature()
    {
        return room.getOptimalMeasurements().getTemperature();
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        room.setOptimalMeasurements(co2,humidity,light,temperature);
        roomRepository.editRoomOptimal(room.getOptimalMeasurements(), position);
    }


}
