package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomB4ViewModel extends AndroidViewModel {

    private ArtworksRepository artworksRepository;

    public RoomB4ViewModel(Application application)  {
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

