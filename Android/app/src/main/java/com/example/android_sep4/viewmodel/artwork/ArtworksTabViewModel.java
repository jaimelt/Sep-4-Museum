package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksTabViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Artwork>> artworksList;
    private ArtworksRepository artworksRepository;

    public ArtworksTabViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        artworksList = artworksRepository.getArtworksData();
    }

    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksList;
    }

    public void removeArtwork(int position) {
        artworksRepository.removeArtwork(position);
    }

    public Artwork getArtwork(int position) {
        return artworksRepository.getArtwork(position);
    }

    public void addArtwork(int position, Artwork artwork) {
        artworksRepository.addArtwork(position, artwork);
    }
}
