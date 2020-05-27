package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomB1ViewModel extends AndroidViewModel {
    private RoomRepository roomRepository;

    public RoomB1ViewModel(Application application)  {
        super(application);
        roomRepository = RoomRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String roomCode) {
        return roomRepository.getArtworksByRoomId(roomCode);
    }
}
