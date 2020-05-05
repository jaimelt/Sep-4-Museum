package com.example.android_sep4.viewmodel.roomList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomsTabViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Room>> roomsList;
    private RoomRepository roomsRepository;


    public void init() {
        if (roomsList != null) {
            return;
        }
        roomsRepository = RoomRepository.getInstance();
        roomsList = roomsRepository.getRoomsData();
    }

    public LiveData<ArrayList<Room>> getRooms() {
        return roomsList;
    }


}
