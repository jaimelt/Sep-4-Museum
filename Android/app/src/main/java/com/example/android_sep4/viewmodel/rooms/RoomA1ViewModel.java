package com.example.android_sep4.viewmodel.rooms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomA1ViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomList;
    private ArtworksRepository artworksRepository;

    public void init(String roomCode) {
        if(artworksInRoomList != null) {
            return;
        }
        artworksRepository = ArtworksRepository.getInstance();
        artworksInRoomList = artworksRepository.getArtworksByRoomId(roomCode);;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom() {
        return artworksInRoomList;
    }
}
