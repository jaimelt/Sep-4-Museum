package com.example.android_sep4.viewmodel.museum.storage;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class StorageViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private ArrayList<Artwork> artworks = new ArrayList<>();
    private MutableLiveData<ArrayList<Artwork>> artworksLive = new MutableLiveData<>();

    public StorageViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);

    }

    public LiveData<ArrayList<Artwork>> getArtworksFromStorage(String id) {

        artworksRepository.getArtworksFromStorage(id).observeForever(artworks -> artworksLive.setValue(artworks));
        return artworksLive;
    }

    public void removeArtwork(int id) {
        artworksRepository.deleteArtwork(id);
    }

    public LiveData<Boolean> getIsLoading() {
        return artworksRepository.getIsLoading();
    }

    public void deleteArtwork(int position) {
        artworks = new ArrayList<>();
        int id = 0;
        artworks.addAll(artworksLive.getValue());
        id = artworks.get(position).getId();
        removeArtwork(id);
    }
}
