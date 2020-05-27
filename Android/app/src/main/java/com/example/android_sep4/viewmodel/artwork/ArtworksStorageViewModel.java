package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksStorageViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public ArtworksStorageViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksRepository.getArtworksData();
    }

    public void removeArtwork(int position) {
        artworksRepository.positionToId(position);
    }

//    public Artwork getArtwork(int id) {
////        return artworksRepository.getArtworkById(id);
//    }

    public void addArtwork(int position, Artwork artwork) {
        artworksRepository.addNewArtwork(artwork);
    }
}
