package com.example.android_sep4.viewmodel.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class RoomB3ViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomList;
    private ArtworksRepository artworksRepository;

    public RoomB3ViewModel(Application application, String roomCode)  {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        artworksInRoomList = artworksRepository.getArtworksByRoomId(roomCode);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom() {
        return artworksInRoomList;
    }

}
