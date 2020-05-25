package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomA2ViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;

    public RoomA2ViewModel(Application application)  {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom() {
//WE WILL NEED TO PASS THE ROOM CODE
        String roomCode = "asfas";
        return roomRepository.getArtworksByRoomId(roomCode);
    }
}
