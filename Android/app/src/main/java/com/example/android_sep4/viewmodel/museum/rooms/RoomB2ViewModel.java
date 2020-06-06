package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class RoomB2ViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public RoomB2ViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String roomCode) {
        return artworksRepository.getArtworksByRoomId(roomCode);
    }

    public LiveData<Boolean> getIsLoading() {
        return artworksRepository.getIsLoading();
    }

}

