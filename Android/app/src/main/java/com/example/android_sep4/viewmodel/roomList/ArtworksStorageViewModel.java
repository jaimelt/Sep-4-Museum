package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class ArtworksStorageViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private RoomRepository roomRepository;
    private ArrayList<Artwork> artworks = new ArrayList<>();
    private LiveData<ArrayList<Artwork>> artworksLive;
    public ArtworksStorageViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        roomRepository = RoomRepository.getInstance(application);

    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String id) {
        artworksLive = roomRepository.getArtworksByRoomId(id);
        return artworksLive;
    }

    public void removeArtwork(int id) {
         artworksRepository.deleteArtwork(id);
    }

    public LiveData<Boolean> getIsLoading() {
        return roomRepository.getIsLoading();
    }

    public void positionToId(int position) {
        artworks.addAll(artworksLive.getValue());
        int id = 0;
        id = artworks.get(position).getId();
        System.out.println(id + "viewmodel id");
        removeArtwork(id);
    }
}
