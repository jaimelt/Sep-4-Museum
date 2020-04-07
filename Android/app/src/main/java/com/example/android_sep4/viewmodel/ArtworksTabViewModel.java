package com.example.android_sep4.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksTabViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Artwork>> artworksList;
    private ArtworksRepository artworksRepository;

    public void init() {
        if(artworksList != null) {
            return;
        }
        artworksRepository = ArtworksRepository.getInstance();
        artworksList = artworksRepository.getArtworksData();
    }

    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksList;
    }
}
