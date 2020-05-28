package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksFromRoomsRepository;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomA3ViewModel extends AndroidViewModel {
    private ArtworksFromRoomsRepository artworksFromRoomsRepository;

    public RoomA3ViewModel(Application application) {
        super(application);
        artworksFromRoomsRepository = ArtworksFromRoomsRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String roomCode) {
        return artworksFromRoomsRepository.getArtworkFromA3(roomCode);
    }
}