package com.example.android_sep4.viewmodel.roomList;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;
import com.example.android_sep4.repositories.RoomRepository;

import java.util.ArrayList;

public class ArtworksStorageViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private ArrayList<Artwork> artworks = new ArrayList<>();
    private MutableLiveData<ArrayList<Artwork>> artworksLive = new MutableLiveData<>();
    public ArtworksStorageViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);

    }

    public LiveData<ArrayList<Artwork>> getArtworksFromRoom(String id) {

        artworksRepository.getArtworksByRoomId(id).observeForever(new Observer<ArrayList<Artwork>>() {
            @Override
            public void onChanged(ArrayList<Artwork> artworks) {
                    artworksLive.setValue(artworks);
            }
        });
        return artworksLive;
    }

    public void removeArtwork(int id) {
         artworksRepository.deleteArtwork(id);
    }

    public LiveData<Boolean> getIsLoading() {
        return artworksRepository.getIsLoading();
    }

    public void positionToId(int position) {
        artworks = new ArrayList<>();
        int id = 0;
        artworks.addAll(artworksLive.getValue());
        id = artworks.get(position).getId();
        removeArtwork(id);
    }
}
