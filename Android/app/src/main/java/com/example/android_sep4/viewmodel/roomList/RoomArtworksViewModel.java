package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomArtworksViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;

    public RoomArtworksViewModel(Application application, String roomCode) {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String locationCode) {
//WE WILL NEED TO PASS THE ROOM CODE
        return roomRepository.getArtworksByRoomId(locationCode);
    }

}
