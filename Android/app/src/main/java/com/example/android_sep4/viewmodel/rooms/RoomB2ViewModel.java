package com.example.android_sep4.viewmodel.rooms;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class RoomB2ViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public RoomB2ViewModel(Application application)  {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom() {
        //WE WILL NEED TO PASS THE ROOM CODE
        String roomCode = "asfas";
        return artworksRepository.getArtworksByRoomId(roomCode);
    }

}
