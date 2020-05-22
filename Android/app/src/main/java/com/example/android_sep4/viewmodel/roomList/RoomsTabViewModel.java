package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomsTabViewModel extends AndroidViewModel {
    private RoomRepository roomsRepository;

    public RoomsTabViewModel(Application application) {
        super(application);
        roomsRepository = RoomRepository.getInstance();
    }

    public LiveData<ArrayList<Room>> getRooms() {
        return roomsRepository.getRoomsData();
    }


}
