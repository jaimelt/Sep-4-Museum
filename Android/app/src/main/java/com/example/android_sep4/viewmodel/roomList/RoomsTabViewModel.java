package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomsTabViewModel extends AndroidViewModel {
    private RoomRepository roomsRepository;

    public RoomsTabViewModel(Application application) {
        super(application);
        roomsRepository = RoomRepository.getInstance(application);
    }

    public LiveData<Rooms> getRoomsLive() {
        return roomsRepository.getRoomsDataLive();
    }

    public void getRooms()
    {
        roomsRepository.getRoomsData();
    }

/*    public LiveData<RoomMeasurements> getLiveMeasurements(String roomCode){
        return  roomsRepository.getLiveMeasurements(roomCode);
    }*/

    public LiveData<Boolean> getIsLoading() {
        return roomsRepository.getIsLoading();
    }


}
