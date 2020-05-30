package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class RoomArtworksViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public RoomArtworksViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String locationCode) {
        return artworksRepository.getArtworksByRoomId(locationCode);
    }

    public LiveData<Boolean> getIsLoading() {
        return artworksRepository.getIsLoading();
    }

}
