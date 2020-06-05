package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Validator;
import com.example.android_sep4.repositories.RoomRepository;

public class EditRoomsConditionsViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;
    private Room room;
    private int position;
    private Validator validator;

    public EditRoomsConditionsViewModel(@NonNull Application application) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
        validator = new Validator();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getCo2() {
        return room.getCo2();
    }

    public double getHumidity() {
        return room.getHumidity();
    }

    public double getLight() {
        return room.getLight();
    }

    public double getTemperature() {
        return room.getTemperature();
    }

    public void editRoomOptimal(String locationCode, String description, int totalCapacity, int currentCapacity,  double light, double co2,
                                double temperature, double humidity, RoomMeasurements roomMeasurements) {
        Room room = new Room(locationCode, description, totalCapacity, currentCapacity, null, light, temperature, humidity, co2, roomMeasurements);
        roomRepository.editRoomOptimal(room);
    }

    public int validateEditRoomFields(String lightText, String co2Text, String temperatureText, String humidityText) {
        return validator.validateEditRoomFields(lightText, co2Text, temperatureText, humidityText);
    }
}
