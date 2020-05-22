package com.example.android_sep4.viewmodel.roomList;

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

    public int getCo2() {
        return room.getCo2();
    }

    public int getHumidity() {
        return room.getHumidity();
    }

    public int getLight() {
        return room.getLight();
    }

    public int getTemperature() {
        return room.getTemperature();
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomRepository.editRoomOptimal(light, co2, temperature, humidity, position);
    }


}
