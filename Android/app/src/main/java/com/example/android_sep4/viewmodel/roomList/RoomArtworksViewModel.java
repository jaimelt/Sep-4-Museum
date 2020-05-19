package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class RoomArtworksViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public RoomArtworksViewModel(Application application, String roomCode) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom() {
//WE WILL NEED TO PASS THE ROOM CODE
        String roomCode = "asfas";
        return artworksRepository.getArtworksByRoomId(roomCode);
    }

}
